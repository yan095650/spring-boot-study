package com.ykp.springbootdemo.manager;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

public class BaseManager {
	
	@PostConstruct
	public void init() {
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for ( int i = 0; i < fields.length; i++ ) {
				String fieldname = fields[ i ].getName();
				if ( fieldname.startsWith( "_" ) ) {
					String sfieldname = fieldname.substring( 1 );
					Field sfield = this.getClass().getDeclaredField( sfieldname );
					sfield.setAccessible( true );
					sfield.set( this, fields[ i ].get( this ) );
				}
			}
		} catch ( IllegalArgumentException e ) {
			e.printStackTrace();
		} catch ( NoSuchFieldException e ) {
			e.printStackTrace();
		} catch ( SecurityException e ) {
			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			e.printStackTrace();
		}
	}
}
