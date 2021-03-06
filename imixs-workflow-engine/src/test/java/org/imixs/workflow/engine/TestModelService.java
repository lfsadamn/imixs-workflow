package org.imixs.workflow.engine;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Model;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Test class for WorkflowService
 * 
 * This test verifies specific method implementations of the workflowService by
 * mocking the WorkflowService with the @spy annotation.
 * 
 * 
 * @author rsoika
 */
public class TestModelService extends WorkflowMockEnvironment {
	public static final String DEFAULT_MODEL_VERSION="1.0.0";

	@Before
	public void setup() throws PluginException, ModelException {
		this.setModelPath("/bpmn/TestWorkflowService.bpmn");
		super.setup();
	}

	/**
	 * This deprecated model version 
	 * 
	 * 
	 */
	@Test
	public void testDeprecatedModelVersion()  {
		
		// load test workitem
		ItemCollection workitem = database.get("W0000-00001");
		workitem.setModelVersion("0.9.0");
		workitem.setTaskID(100);
		workitem.setEventID(10);
		workitem.replaceItemValue("txtWorkflowGroup","Ticket");
	
		Model amodel=null;
		try {
			amodel = modelService.getModelByWorkitem(workitem);
		} catch (ModelException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(amodel);
		Assert.assertEquals("1.0.0", amodel.getVersion());

	}

}
