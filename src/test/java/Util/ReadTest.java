package Util;


import org.junit.Test;


import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


public class ReadTest {


    @Test
    public void should_get_number_of_verticles() {
        //given
        Read read = new Read();
        //when
        try {
            read.readFile("6");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertThat(read.getNumberVerticles()).isEqualTo(6);
    }

    @Test
    public void should_not_empty_cost_path() {
        //given
        Read read = new Read();
        //when
        try {
            read.readFile("6");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertThat(read.getCost()).isNotEmpty();
    }

    @Test
    public void should_value_path_0_0() {
        //given
        Read read = new Read();
        //when
        try {
            read.readFile("6");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //then
        assertThat(read.getCost()[0][0]).isEqualTo(-1);
    }
    @Test
    public void should_value_path_max_max() {
        //given
        Read read = new Read();
        //when
        try {
            read.readFile("6");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int max = read.getNumberVerticles()-1;
        //then
        assertThat(read.getCost()[max][max]).isEqualTo(-1);
    }
}
