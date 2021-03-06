/*
 * Created on 2004-12-01
 *
 */
package org.hibernate.tool.hbm2x.Hbm2JavaDidirectionalIndexedCollectionMappingTest;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2x.Exporter;
import org.hibernate.tool.hbm2x.POJOExporter;
import org.hibernate.tools.test.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author max
 * @author koen
 */
public class TestCase {

	private static final String[] HBM_XML_FILES = new String[] {
			"GenericModel.hbm.xml"
	};
	
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private File outputDir = null;

	@Before
	public void setUp() throws Exception {
		Configuration configuration = 
				HibernateUtil.initializeConfiguration(this, HBM_XML_FILES);
		outputDir = temporaryFolder.getRoot();
		Exporter exporter = new POJOExporter();
		exporter.setConfiguration(configuration);
		exporter.setOutputDirectory(outputDir);
		exporter.start();
	}
	
	@Test
	public void testReflection() throws Exception {
		Assert.assertTrue(new File(outputDir, "GenericObject.java").exists());
		Assert.assertTrue(new File(outputDir, "GenericValue.java").exists());
	}
	
}
