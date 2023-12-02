/*******************************************************************************
 * Copyright (c) 2013-2020 LAAS-CNRS (www.laas.fr)
 * 7 Colonel Roche 31077 Toulouse - France
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Initial Contributors:
 *     Thierry Monteil : Project manager, technical co-manager
 *     Mahdi Ben Alaya : Technical co-manager
 *     Samir Medjiah : Technical co-manager
 *     Khalil Drira : Strategy expert
 *     Guillaume Garzone : Developer
 *     François Aïssaoui : Developer
 *
 * New contributors :
 *******************************************************************************/
package org.eclipse.om2m.ipe.sample.controller;

import java.math.BigInteger;

import org.eclipse.om2m.commons.constants.MimeMediaType;
import org.eclipse.om2m.commons.constants.ResponseStatusCode;
import org.eclipse.om2m.commons.resource.AE;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.ResponsePrimitive;
import org.eclipse.om2m.ipe.sample.RequestSender;
import org.eclipse.om2m.ipe.sample.constants.SampleConstants;
import org.eclipse.om2m.ipe.sample.model.Lamp.LampModel;
import org.eclipse.om2m.ipe.sample.model.Lamp.SpringLamp;
import org.eclipse.om2m.ipe.sample.util.ObixUtil;

public class LifeCycleManager {

	/**
	 *  1. AE 등록 --> AE를 생성할 때
	 */
	public static void start(){
		String lampId = SpringLamp.TYPE+"_"+1;
		SpringLamp lamp = new SpringLamp(lampId,false);
		LampModel.setModel(lamp);
		createLampResources(lampId, false, SampleConstants.POA);
		
		String	deviceId = Device.TYPE + "_" + 1;
		Device	device = new Device(deviceId);
		String	TEMPERATURE = device.getTEMPERATURE();
		String	HUMIDITY = device.getHUMIDITY();
		createDeviceResources(deviceId, TEMPERATURE, HUMIDITY, SampleConstants.POA);
	}

	public static void stop(){
		
	}


	/**
	 * Creates all required resources.
	 * @param appId - Application ID
	 * @param initValue - initial lamp value
	 * @param poa - lamp Point of Access
	 */
	private static void createLampResources(String appId, boolean initValue, String poa) {

		Container container = new Container();
		container.getLabels().add("lamp");
		container.setMaxNrOfInstances(BigInteger.valueOf(0));

		AE ae = new AE();
		ae.setRequestReachability(true);
		ae.getPointOfAccess().add(poa);
		ae.setAppID(appId);
		ae.setName(appId);

		//CSE에 AE 등록되어 있는지 확인
		ResponsePrimitive response = RequestSender.createAE(ae);

		// CSE 최초 등록이면 아래 실행
		if(response.getResponseStatusCode().equals(ResponseStatusCode.CREATED)) {
			container = new Container();
			container.setMaxNrOfInstances(BigInteger.valueOf(10));

			container.setName(SampleConstants.DESC);
			RequestSender.createContainer(response.getLocation(), container);

			container.setName(SampleConstants.DATA);
			RequestSender.createContainer(response.getLocation(), container);

			String content;

			content = ObixUtil.getDescriptorRep(SampleConstants.CSE_ID, appId, SampleConstants.DATA);

			ContentInstance contentInstance = new ContentInstance();
			contentInstance.setContent(content);
			contentInstance.setContentInfo(MimeMediaType.OBIX);
			RequestSender.createContentInstance(
					SampleConstants.CSE_PREFIX + "/" + appId + "/" + SampleConstants.DESC, contentInstance);

			// Create initial contentInstance on the STATE container resource
			content = ObixUtil.getStateRep(appId, initValue); // 처음 시작은 불이 꺼져있는 상태
			contentInstance.setContent(content);
			RequestSender.createContentInstance(
					SampleConstants.CSE_PREFIX + "/" + appId + "/" + SampleConstants.DATA, contentInstance);
		}
	}
	
	private static void createDeviceResources(String appId, String TEMPERATURE, String HUMIDITY, String poa) {

		Container container = new Container();
		container.getLabels().add("Device");
		container.setMaxNrOfInstances(BigInteger.valueOf(0));

		AE ae = new AE();
		ae.setRequestReachability(true);
		ae.getPointOfAccess().add(poa);
		ae.setAppID(appId);
		ae.setName(appId);

		//CSE에 AE 등록되어 있는지 확인
		ResponsePrimitive response = RequestSender.createAE(ae);

		// CSE 최초 등록이면 아래 실행
		if(response.getResponseStatusCode().equals(ResponseStatusCode.CREATED)) {
			container = new Container();
			container.setMaxNrOfInstances(BigInteger.valueOf(10));

			container.setName(SampleConstants.DESC);
			RequestSender.createContainer(response.getLocation(), container);

			container.setName(SampleConstants.DATA);
			RequestSender.createContainer(response.getLocation(), container);

			String content;

			content = ObixUtil.getDescriptorRep(SampleConstants.CSE_ID, appId, SampleConstants.DATA);

			ContentInstance contentInstance = new ContentInstance();
			contentInstance.setContent(content);
			contentInstance.setContentInfo(MimeMediaType.OBIX);
			RequestSender.createContentInstance(
					SampleConstants.CSE_PREFIX + "/" + appId + "/" + SampleConstants.DESC, contentInstance);

			// Create initial contentInstance on the STATE container resource
			content = ObixUtil.getStateRepDev(appId, initValue); // Device;
			contentInstance.setContent(content);
			RequestSender.createContentInstance(
					SampleConstants.CSE_PREFIX + "/" + appId + "/" + SampleConstants.DATA, contentInstance);
		}
	}
}
