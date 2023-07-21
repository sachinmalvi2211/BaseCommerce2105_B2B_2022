/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.demo.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.demo.core.constants.DemoCoreConstants;
import de.hybris.demo.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
public class DemoCoreManager extends GeneratedDemoCoreManager
{
	public static final DemoCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (DemoCoreManager) em.getExtension(DemoCoreConstants.EXTENSIONNAME);
	}
}
