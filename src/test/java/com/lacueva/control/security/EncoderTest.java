package com.lacueva.control.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EncoderTest {

    @Inject
    private StandardPasswordEncoder encoder;

    @Test
    public void testEncode() {
	assertTrue(encoder.matches("koala", encoder.encode("koala")));
	assertFalse(encoder.matches("koala", encoder.encode("test")));
	assertTrue(encoder.matches("koala",
		"4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"));
	assertFalse(encoder.matches("koala",
		"100fec8e8e0f9e63ebd4064919dea351b88a3b043c89e89f1bbf6202770b705a1a9bbd3489eed819"));
    }
}
