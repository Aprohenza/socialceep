package com.formbean.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.formbean.entity.UserEntity;
import com.formbean.session.UserOwnPost;

import java.io.StringWriter;
import javax.json.Json;



public class ObjectEncoder implements Encoder.Text<UserOwnPost> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public String encode(UserOwnPost post) throws EncodeException {		
		
		StringWriter writer = new StringWriter();		

		Json.createGenerator(writer)
			.writeStartObject()
				.write("postAuthorId", post.getPostAuthorId())
				.write("postAuthorName", post.getPostAuthorName())
				.write("postAuthorPhoto", post.getPostAuthorPhoto())
				.write("postAuthorRole", post.getPostAuthorRole())
				.write("postBody", post.getPostBody())
			.writeEnd()
			.flush();
		
		System.out.println(writer.toString());
		
		return writer.toString();
		
	}

}
