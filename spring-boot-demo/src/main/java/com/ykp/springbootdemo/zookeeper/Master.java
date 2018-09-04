package com.ykp.springbootdemo.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 操作zookeeper
 */
public class Master implements Watcher {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	private ZooKeeper zooKeeper;
	
	public void startZK( String zkHost, Integer zkSessionTimeout ) throws IOException {
		zooKeeper = new ZooKeeper( zkHost, zkSessionTimeout, this );
	}
	
	@Override
	public void process( WatchedEvent event ) {
		logger.info( "WatchedEvent：{}", event );
		try {
			List<String> childList = zooKeeper.getChildren( "/", true );
			for ( String child : childList ) {
				logger.info( "child：{}", child );
			}
		} catch ( KeeperException e ) {
			e.printStackTrace();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
