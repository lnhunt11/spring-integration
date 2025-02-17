/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.ftp;

import org.junit.jupiter.api.Test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Zhurakousky
 * @author Gunnar Hillert
 * @author Gary Russell
 *
 */
public class FtpMessageHistoryTests {

	@Test
	public void testMessageHistory() throws Exception {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ftp-message-history-context.xml",
				this.getClass());
		SourcePollingChannelAdapter adapter = ac.getBean("adapterFtp", SourcePollingChannelAdapter.class);
		assertThat(adapter.getComponentName()).isEqualTo("adapterFtp");
		assertThat(adapter.getComponentType()).isEqualTo("ftp:inbound-channel-adapter");
		ac.close();
	}

}
