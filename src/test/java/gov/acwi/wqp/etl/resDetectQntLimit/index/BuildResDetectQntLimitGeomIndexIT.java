package gov.acwi.wqp.etl.resDetectQntLimit.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildResDetectQntLimitGeomIndexIT extends BaseBuildResDetectQntLimitIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/resDetectQntLimit/indexes/geom.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='r_detect_qnt_lmt_swap_testsrc_geom'")
	public void buildResDetectQntLimitGeomIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResDetectQntLimitGeomIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}