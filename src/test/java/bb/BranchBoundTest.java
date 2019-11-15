package bb;
import Util.Read;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class BranchBoundTest {
    Read read = new Read();
    @Before
    public void init() throws FileNotFoundException {
        read.readFile("6");
    }

    @Test
    public void algorithm() {
        //given
        BranchBound branchBound = new BranchBound(read.getNumberVerticles(),read.getCost());
        int[] path = {0, 5,1,2,3,4,0};

        //when
        branchBound.algorithm(0);
        //then
        assertThat(branchBound.getCost()).isEqualTo(80);
        assertThat(branchBound.getPath()).isEqualTo(path);
    }

    @Test
    public void should_copy_Path()  {
        //given
        BranchBound branchBound = new BranchBound(read.getNumberVerticles(),read.getCost());
       branchBound.algorithm(0);
        //when
        branchBound.copyPath();
        ;
        //then
        assertThat(branchBound.getPath()[read.getNumberVerticles()-1]).isEqualTo(branchBound.getTempPath()[read.getNumberVerticles()-1]);
    }

    @Test
    public void should_search_Min() {
        //given
        BranchBound branchBound = new BranchBound(read.getNumberVerticles(),read.getCost());
        //when
        int min = branchBound.searchMin(0);
        //then
        assertThat(min).isEqualTo(4);
    }

    @Test
    public void should_calculate_Bound() {
        //given
        BranchBound branchBound = new BranchBound(read.getNumberVerticles(),read.getCost());
        //when
        int bound = branchBound.calculateBound(0,1);
        //then
        assertThat(bound).isEqualTo(4);
    }
}
