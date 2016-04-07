package br.com.erudio.converter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.erudio.mocks.PersonMock;
import br.com.erudio.vo.PersonVO;

public class OrikaParserTest {

    PersonMock inputObject;

    @Before
    public void setUp() {
    	inputObject = new PersonMock();
    }

    @Test
    public void parseObjectInputToObjectOutputTest() {
        PersonVO output = OrikaParser.parseObjectInputToObjectOutput(inputObject.mockPerson(1), PersonVO.class);
        System.out.println(output.getAddresses().get(0).getPostalCode());
        assertEquals("ONE PERSON 1", output.getName());
        assertEquals("34700-370", output.getAddresses().get(0).getPostalCode());
//        assertTrue(output.getAge() == 21);
    }

    @Test
    public void parserListObjectInputToObjectOutputTest() {
        List<PersonVO> output = OrikaParser.parserListObjectInputToObjectOutput(inputObject.mockPersonsList(9), PersonVO.class);
//        System.out.println(output.get(0).getAddresses().get(0).getPostalCode());
        assertEquals("ONE PERSON 1", output.get(0).getName());
//        assertEquals("Old City", output.get(0).getAddress().getTown());
//        assertTrue(output.get(0).getAge() == 22);
    }

}
