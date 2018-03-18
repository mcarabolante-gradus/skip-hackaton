package org.mcarabolante.resources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FirstResourceTest {

    @Test
    public void something(){
        assertThat(1 + 1, is(2));
    }

}