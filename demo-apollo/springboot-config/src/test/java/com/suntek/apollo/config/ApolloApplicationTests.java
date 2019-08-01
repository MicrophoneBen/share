package com.suntek.apollo.config;

import com.suntek.apollo.config.dto.ConfigDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApolloApplicationTests {

	@Autowired
	@Qualifier("ApolloConfig")
    private ConfigDto configDto;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetConfig(){
		System.out.println(configDto.toString());

		ExpressionParser parser =new SpelExpressionParser();
		StandardEvaluationContext context =new StandardEvaluationContext();
		context.setVariable("message", "Hello World");
		String value =parser.parseExpression("#redis.host").getValue(context, String.class);
		System.out.println(value);
	}
}
