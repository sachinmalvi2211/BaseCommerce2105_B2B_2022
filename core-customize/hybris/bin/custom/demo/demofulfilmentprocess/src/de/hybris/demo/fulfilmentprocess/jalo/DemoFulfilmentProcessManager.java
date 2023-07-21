/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.demo.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.demo.fulfilmentprocess.constants.DemoFulfilmentProcessConstants;

public class DemoFulfilmentProcessManager extends GeneratedDemoFulfilmentProcessManager
{
	public static final DemoFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (DemoFulfilmentProcessManager) em.getExtension(DemoFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
