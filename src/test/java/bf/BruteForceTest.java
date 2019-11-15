package bf;


import Util.Read;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


public class BruteForceTest {

    @Test
    public void should_the_same_number_of_vertucles() throws FileNotFoundException {
        //given
        Read read = new Read();
        read.readFile("6");
        BruteForce bruteForce = new BruteForce(read.getNumberVerticles(),read.getCost());
        //when
        bruteForce.algorithm(0);
        //given
        assertThat(read.getNumberVerticles()).isEqualTo(bruteForce.getNumberOFVertices());
    }
    @Test
    public void should_the_same_cost_when_different_point_start() throws FileNotFoundException {
        //given
        Read read = new Read();
        read.readFile("6");
        BruteForce bruteForce = new BruteForce(read.getNumberVerticles(),read.getCost());
        //when
        bruteForce.algorithm(0);
       int point0 = bruteForce.getCost();
       bruteForce.algorithm(5);
       int point5 = bruteForce.getCost();
        //given
        assertThat(point0).isEqualTo(point5);
    }
    @Test
    public void should_the_same_path() throws FileNotFoundException {
        //given
        Read read = new Read();
        read.readFile("6");
        BruteForce bruteForce = new BruteForce(read.getNumberVerticles(),read.getCost());
        //when
        bruteForce.algorithm(0);
        int[] point0 = bruteForce.getPath();
        bruteForce.algorithm(0);
        int[] point = bruteForce.getPath();
        //given
        assertThat(point0).isEqualTo(point);
    }
    @Test
    public void should_path_is_not_zero() throws FileNotFoundException {
        //given
        Read read = new Read();
        read.readFile("6");
        BruteForce bruteForce = new BruteForce(read.getNumberVerticles(),read.getCost());
        int[] point = new int[read.getNumberVerticles()];
        //when
        bruteForce.algorithm(0);
        int[] point0 = bruteForce.getPath();


        //given
        assertThat(point0).isNotEqualTo(point);
    }
}
