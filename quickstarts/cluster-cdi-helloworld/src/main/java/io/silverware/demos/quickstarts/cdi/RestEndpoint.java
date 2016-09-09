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
package io.silverware.demos.quickstarts.cdi;

import static java.util.UUID.randomUUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import java.util.Random;

import io.silverware.demos.quickstarts.cdi.CustomObject.BrokenInnerClass;
import io.silverware.microservices.annotations.Gateway;
import io.silverware.microservices.annotations.Microservice;
import io.silverware.microservices.annotations.MicroserviceReference;

/**
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
@Gateway
@Microservice
public class RestEndpoint {

	private static final Logger log = LogManager.getLogger(RestEndpoint.class);

	@Inject
	@MicroserviceReference
	private ClusteredHelloWorldService other;

	public void cdiHello() {
		Random rnd = new Random();
		log.info("start cdi calls");
		other.hello();
		log.info("Addition result is: {} ", other.magicCount(rnd.nextInt(10), rnd.nextInt(10)));
		log.info("Multiplication result is: {} ", other.magicCount(rnd.nextInt(10), new Integer(rnd.nextInt(10))));
		log.info("Custom class call result is: {} ", other.customSerialization(new CustomObject(rnd.nextInt(10), randomUUID().toString(), rnd.nextFloat() % 100, new BrokenInnerClass(randomUUID()))));
		log.info("Custom class call with null parameter result is: {} ", other.customSerialization(null));
		log.info("end cdi calls");
	}

}