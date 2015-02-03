package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class OpenMP2CIVLTransformerTestDev {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "omp");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */
/*
	@Test
	public void dotProduct1() {
		assertTrue(ui.run("verify ", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("dotProduct1.c")));
	}
	
	@Test
	public void dotProduct1Simplify() {
		assertTrue(ui.run("verify ",
				"-inputTHREAD_MAX=2", filename("dotProduct1.c")));
	}

	@Test
	public void dotProductCritical() {
		assertTrue(ui.run("verify ", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("dotProduct_critical.c")));
	}
	
	@Test
	public void dotProductCriticalSimplify() {
		assertTrue(ui.run("verify ",
				"-inputTHREAD_MAX=2", filename("dotProduct_critical.c")));
	}

	@Test
	public void matProduct1() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("matProduct1.c")));
	}
	
	@Test
	public void matProduct1Simplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("matProduct1.c")));
	}

	@Test
	public void parallelfor() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("parallelfor.c")));
	}
	
	@Test
	public void parallelforSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("parallelfor.c")));
	}

	@Test
	public void raceCond1() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("raceCond1.c")));
	}
	
	@Test
	public void raceCond1Simplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("raceCond1.c")));
	}
	
	@Test
	public void fft() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("fft_openmp.c")));
	}
	
	@Test
	public void fftSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("fft_openmp.c")));
	}
	
	@Test
	public void poisson() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("poisson_openmp.c")));
	}
	
	@Test
	public void poissonSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("poisson_openmp.c")));
	}

	@Test
	public void quad() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("quad_openmp.c")));
	}
	
	@Test
	public void quadSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("quad_openmp.c")));
	}
	
	@Test
	public void md() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("md_openmp.c")));
	}
	
	@Test
	public void mdSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("md_openmp.c")));
	}

	@Test
	public void heatedplate() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("heated_plate_openmp.c")));
	}
	
	@Test
	public void heatedplateSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("heated_plate_openmp.c")));
	}
	@Test
	public void prime() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("prime_openmp.c")));
	}
	
	@Test
	public void primeSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("prime_openmp.c")));
	}
	
	@Test
	public void fig310_mxv_omp() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("fig310-mxv-omp.c")));
	}
	
	@Test
	public void fig310_mxv_ompSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("fig310-mxv-omp.c")));
	}
	
	@Test
	public void pi() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("pi.c")));
	}
	
	@Test
	public void piSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("pi.c")));
	}
	*/
	
	@Test
        public void cmandel() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_mandel.c")));
        }

        @Ignore 
        @Test
        public void cmandelSimplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_mandel.c")));
        }

        @Test
        public void cmd() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_md.c")));
        }

        @Ignore 
        @Test
        public void cmdSimplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_md.c")));
        }

        @Test
        public void cpi() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_pi.c")));
        }

        @Ignore 
        @Test
        public void cpiSimplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_pi.c")));
        }

        @Test
        public void cfft() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_fft.c")));
        }
        
        @Ignore 
        @Test
        public void cfftSimplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_fft.c")));
        }

        @Test        public void cfft6() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_fft6.c")));
        }
        
        @Ignore 
        @Test
        public void cfft6Simplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_fft6.c")));
        }

        @Test
        public void cjacobi01() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi01.c")));
        }

        @Ignore
        @Test
        public void cjacobi01Simplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi01.c")));
        }

        @Test
        public void cjacobi02() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi02.c")));
        }

        @Ignore
        @Test
        public void cjacobi02Simplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi02.c")));
        }

        @Test
        public void cjacobi03() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi03.c")));
        }

        @Ignore
        @Test
        public void cjacobi03Simplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_jacobi03.c")));
        }

        @Test
        public void clu() {
                assertTrue(ui.run("verify", "-ompNoSimplify",
                                "-inputTHREAD_MAX=2", filename("c_lu.c")));
        }

        @Ignore
        @Test
        public void cluSimplify() {
                assertTrue(ui.run("verify",
                                "-inputTHREAD_MAX=2", filename("c_lu.c")));
        }
        /*
	
	@Test
	public void forWorkshare() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2 -showAmpleSetWtStates", filename("for.c")));
	}
	
	@Test
	public void singleWorkshare() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2 -showAmpleSetWtStates", filename("single.c")));
	}
	*/
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
