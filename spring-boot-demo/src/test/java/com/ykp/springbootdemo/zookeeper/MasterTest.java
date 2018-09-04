package com.ykp.springbootdemo.zookeeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith( SpringRunner.class )
@SpringBootTest
public class MasterTest {
	
	@Value( "${zk.host}" )
	private String zkHost;
	
	@Value( "${zk.session-timeout}" )
	private Integer zkSessionTimeout;
	
	@Test
	public void test_process() throws IOException, InterruptedException {
		Master master = new Master();
		master.startZK( zkHost, zkSessionTimeout );
		Thread.sleep( 3000 );
	}
}
