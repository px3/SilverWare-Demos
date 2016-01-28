/*
 * -----------------------------------------------------------------------\
 * SilverWare
 *  
 * Copyright (C) 2010 - 2013 the original author or authors.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -----------------------------------------------------------------------/
 */
package io.silverware.demos.devconf2016.intelligent_home;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author <a href="mailto:marvenec@gmail.com">Martin Večeřa</a>
 */
public class RgbLedRouteBuilder extends RouteBuilder {
   @Override
   public void configure() throws Exception {
      final RgbLedProcessor rgbLedProcessor = new RgbLedProcessor();

      // HTTP query led, r, g, b required
      from("jetty:http://0.0.0.0:8282/led/setrgb")
            // set R channel value
            .setHeader("channel", simple("r"))
            .process(rgbLedProcessor)
            .to("direct:pca9685-pwm-set")

            // set G channel value
            .setHeader("channel", simple("g"))
            .process(rgbLedProcessor)
            .to("direct:pca9685-pwm-set")

            // set B channel value
            .setHeader("channel", simple("b"))
            .process(rgbLedProcessor)
            .to("direct:pca9685-pwm-set");

   }

}