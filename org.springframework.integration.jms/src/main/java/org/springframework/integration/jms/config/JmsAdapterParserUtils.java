/*
 * Copyright 2002-2010 the original author or authors.
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
 */

package org.springframework.integration.jms.config;

import org.w3c.dom.Element;

import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;

/**
 * Utility methods and constants for JMS adapter parsers.
 * 
 * @author Mark Fisher
 */
abstract class JmsAdapterParserUtils {

	static final String JMS_TEMPLATE_ATTRIBUTE = "jms-template";

	static final String JMS_TEMPLATE_PROPERTY = "jmsTemplate";

	static final String CONNECTION_FACTORY_ATTRIBUTE = "connection-factory";

	static final String CONNECTION_FACTORY_PROPERTY = "connectionFactory";

	static final String DESTINATION_ATTRIBUTE = "destination";

	static final String DESTINATION_PROPERTY = "destination";

	static final String DESTINATION_NAME_ATTRIBUTE = "destination-name";

	static final String PUB_SUB_DOMAIN_ATTRIBUTE = "pub-sub-domain";

	static final String PUB_SUB_DOMAIN_PROPERTY = "pubSubDomain";

	static final String DESTINATION_NAME_PROPERTY = "destinationName";

	static final String HEADER_MAPPER_ATTRIBUTE = "header-mapper";

	static final String HEADER_MAPPER_PROPERTY = "headerMapper";

	/*
	 * The following constants match those of javax.jms.Session.
	 * They are duplicated here to avoid a dependency in tooling.
	 */

	static final int SESSION_TRANSACTED = 0;

	private static final int AUTO_ACKNOWLEDGE = 1;

	private static final int CLIENT_ACKNOWLEDGE = 2;

	private static final int DUPS_OK_ACKNOWLEDGE = 3;


	static String determineConnectionFactoryBeanName(Element element, ParserContext parserContext) {
		String connectionFactoryBeanName = "connectionFactory";
		if (element.hasAttribute(CONNECTION_FACTORY_ATTRIBUTE)) {
			connectionFactoryBeanName = element.getAttribute(CONNECTION_FACTORY_ATTRIBUTE);
			if (!StringUtils.hasText(connectionFactoryBeanName)) {
				parserContext.getReaderContext().error(
						"JMS adapter 'connection-factory' attribute must not be empty", element);
			}
		}
		return connectionFactoryBeanName;
	}

	static Integer parseAcknowledgeMode(Element element, ParserContext parserContext) {
		String acknowledge = element.getAttribute("acknowledge");
		if (StringUtils.hasText(acknowledge)) {
			int acknowledgeMode = AUTO_ACKNOWLEDGE;
			if ("transacted".equals(acknowledge)) {
				acknowledgeMode = SESSION_TRANSACTED;
			}
			else if ("dups-ok".equals(acknowledge)) {
				acknowledgeMode = DUPS_OK_ACKNOWLEDGE;
			}
			else if ("client".equals(acknowledge)) {
				acknowledgeMode = CLIENT_ACKNOWLEDGE;
			}
			else if (!"auto".equals(acknowledge)) {
				parserContext.getReaderContext().error("Invalid JMS 'acknowledge' setting: " +
						"only \"auto\", \"client\", \"dups-ok\" and \"transacted\" supported.", element);
			}
			return acknowledgeMode;
		}
		else {
			return null;
		}
	}

}
