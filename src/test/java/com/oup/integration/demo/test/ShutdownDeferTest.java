package com.oup.integration.demo.test;

import static org.apache.camel.ShutdownRoute.Defer;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.camel.Component;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileConsumer;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.component.file.GenericFileOperations;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class ShutdownDeferTest extends CamelTestSupport {

    private static final AtomicBoolean CONSUMER_SUSPENDED = new AtomicBoolean();

    @Override
    @Before
    public void setUp() throws Exception {
        deleteDirectory("target/deferred");
        super.setUp();
    }

    @Test
    public void testShutdownDeferred() throws Exception {
        MockEndpoint bar = getMockEndpoint("mock:bar");
        bar.expectedMinimumMessageCount(1);

        template.sendBody("seda:foo", "A");
        template.sendBody("seda:foo", "B");
        template.sendBody("seda:foo", "C");
        template.sendBody("seda:foo", "D");
        template.sendBody("seda:foo", "E");

        assertMockEndpointsSatisfied();

        Thread.sleep(50);

        context.stop();

        assertFalse("Should not have been suspended", CONSUMER_SUSPENDED.get());
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            // START SNIPPET: e1
            public void configure() throws Exception {
                from("seda:foo")
                    .startupOrder(1)
                    .to("file://target/deferred");

                // use file component to transfer files from route 1 -> route 2 as it
                // will normally suspend, but by deferring this we can let route 1
                // complete while shutting down
                MyDeferFileEndpoint defer = new MyDeferFileEndpoint("file://target/deferred?initialDelay=0&delay=10", getContext().getComponent("file"));
                defer.setFile(new File("target/deferred"));

                from(defer)
                    // defer shutting down this route as the 1st route depends upon it
                    .startupOrder(2).shutdownRoute(Defer)
                    .to("mock:bar");
            }
            // END SNIPPET: e1
        };
    }
    private static final class MyDeferFileEndpoint extends FileEndpoint {

        private MyDeferFileEndpoint(String endpointUri, Component component) {
            super(endpointUri, component);
        }

        @Override
        protected FileConsumer newFileConsumer(Processor processor, GenericFileOperations<File> operations) {
            return new FileConsumer(this, processor, operations, createGenericFileStrategy()) {
                @Override
                protected void doSuspend() throws Exception {
                    CONSUMER_SUSPENDED.set(true);
                    super.doSuspend();
                }
            };
        }
    }
}