package com.xiaan.liangyuan.liangyuanapp.utils;

import java.util.UUID;

/**
 * This class will store the UUID of the GATT services and characteristics
 * Created by kevin .
 */

public class UUIDDatabase {
	/**
	 * Heart rate related UUID
	 */
	public final static UUID UUID_HEART_RATE_SERVICE = UUID.fromString(YSRGattAttributes.HEART_RATE_SERVICE);

	public final static UUID UUID_HEART_RATE_MEASUREMENT = UUID.fromString(YSRGattAttributes.HEART_RATE_MEASUREMENT);

	public final static UUID UUID_BODY_SENSOR_LOCATION = UUID.fromString(YSRGattAttributes.BODY_SENSOR_LOCATION);

	/**
	 * Device information related UUID
	 */
	public final static UUID UUID_DEVICE_INFORMATION_SERVICE = UUID.fromString(YSRGattAttributes.DEVICE_INFORMATION_SERVICE);

	public final static UUID UUID_SYSTEM_ID = UUID.fromString(YSRGattAttributes.SYSTEM_ID);

	public static final UUID UUID_MANUFATURE_NAME_STRING = UUID.fromString(YSRGattAttributes.MANUFACTURER_NAME_STRING);

	public static final UUID UUID_MODEL_NUMBER_STRING = UUID.fromString(YSRGattAttributes.MODEL_NUMBER_STRING);

	public static final UUID UUID_SERIAL_NUMBER_STRING = UUID.fromString(YSRGattAttributes.SERIAL_NUMBER_STRING);

	public static final UUID UUID_HARDWARE_REVISION_STRING = UUID.fromString(YSRGattAttributes.HARDWARE_REVISION_STRING);

	public static final UUID UUID_FIRWARE_REVISION_STRING = UUID.fromString(YSRGattAttributes.FIRMWARE_REVISION_STRING);

	public static final UUID UUID_SOFTWARE_REVISION_STRING = UUID.fromString(YSRGattAttributes.SOFTWARE_REVISION_STRING);

	public static final UUID UUID_PNP_ID = UUID.fromString(YSRGattAttributes.PNP_ID);

	public static final UUID UUID_IEEE = UUID.fromString(YSRGattAttributes.IEEE);


	/**
	 * Health thermometer related UUID
	 */
	public final static UUID UUID_HEALTH_SERVICE = UUID.fromString(YSRGattAttributes.HEALTH_TEMP_SERVICE);

	public final static UUID UUID_HEALTH_THERMOMETER = UUID.fromString(YSRGattAttributes.HEALTH_TEMP_MEASUREMENT);

	public final static UUID UUID_HEALTH_THERMOMETER_SENSOR_LOCATION = UUID.fromString(YSRGattAttributes.TEMPERATURE_TYPE);

	/**
	 * Battery Level related uuid
	 */
	public final static UUID UUID_BATTERY_SERVICE = UUID.fromString(YSRGattAttributes.BATTERY_SERVICE);

	public final static UUID UUID_BATTERY_LEVEL = UUID.fromString(YSRGattAttributes.BATTERY_LEVEL);


	/**
	 * Find me related uuid
	 */
	public final static UUID UUID_IMMEDIATE_ALERT_SERVICE = UUID.fromString(YSRGattAttributes.IMMEDIATE_ALERT_SERVICE);

	public final static UUID UUID_TRANSMISSION_POWER_SERVICE = UUID.fromString(YSRGattAttributes.TRANSMISSION_POWER_SERVICE);

	public final static UUID UUID_ALERT_LEVEL = UUID.fromString(YSRGattAttributes.ALERT_LEVEL);

	public final static UUID UUID_TRANSMISSION_POWER_LEVEL = UUID.fromString(YSRGattAttributes.TRANSMISSION_POWER_LEVEL);


	/**
	 * CapSense related uuid
	 */
	public final static UUID UUID_CAPSENSE_PROXIMITY = UUID.fromString(YSRGattAttributes.CAPSENSE_PROXIMITY);

	public final static UUID UUID_CAPSENSE_SLIDER = UUID.fromString(YSRGattAttributes.CAPSENSE_SLIDER);

	public final static UUID UUID_CAPSENSE_BUTTONS = UUID.fromString(YSRGattAttributes.CAPSENSE_BUTTONS);

	public final static UUID UUID_CAPSENSE_PROXIMITY_CUSTOM = UUID.fromString(YSRGattAttributes.CAPSENSE_PROXIMITY_CUSTOM);

	public final static UUID UUID_CAPSENSE_SLIDER_CUSTOM = UUID.fromString(YSRGattAttributes.CAPSENSE_SLIDER_CUSTOM);

	public final static UUID UUID_CAPSENSE_BUTTONS_CUSTOM = UUID.fromString(YSRGattAttributes.CAPSENSE_BUTTONS_CUSTOM);

	/**
	 * RGB LED related uuid
	 */
	public final static UUID UUID_RGB_LED_SERVICE = UUID.fromString(YSRGattAttributes.RGB_LED_SERVICE);

	public final static UUID UUID_RGB_LED = UUID.fromString(YSRGattAttributes.RGB_LED);

	public final static UUID UUID_RGB_LED_SERVICE_CUSTOM = UUID.fromString(YSRGattAttributes.RGB_LED_SERVICE_CUSTOM);

	public final static UUID UUID_RGB_LED_CUSTOM = UUID.fromString(YSRGattAttributes.RGB_LED_CUSTOM);

	/**
	 * GlucoseService related uuid
	 */
	public final static UUID UUID_GLUCOSE = UUID.fromString(YSRGattAttributes.GLUCOSE_COCNTRN);
	/**
	 * Blood pressure related uuid
	 */
	public final static UUID UUID_BLOOD_PRESSURE_SERVICE = UUID.fromString(YSRGattAttributes.BLOOD_PRESSURE_SERVICE);

	public final static UUID UUID_BLOOD_PRESSURE_MEASUREMENT = UUID.fromString(YSRGattAttributes.BLOOD_PRESSURE_MEASUREMENT);

	/**
	 * Running Speed & Cadence related uuid
	 */
	public final static UUID UUID_RSC_MEASURE = UUID.fromString(YSRGattAttributes.RSC_MEASUREMENT);

	/**
	 * Cycling Speed & Cadence related uuid
	 */
	public final static UUID UUID_CSC_MEASURE = UUID.fromString(YSRGattAttributes.CSC_MEASUREMENT);
	/**
	 * Barometer related uuid
	 */
	public final static UUID UUID_BAROMETER_SERVICE = UUID.fromString(YSRGattAttributes.BAROMETER_SERVICE);

	public final static UUID UUID_BAROMETER_DIGITAL_SENSOR = UUID.fromString(YSRGattAttributes.BAROMETER_DIGITAL_SENSOR);

	public final static UUID UUID_BAROMETER_SENSOR_SCAN_INTERVAL = UUID.fromString(YSRGattAttributes.BAROMETER_SENSOR_SCAN_INTERVAL);

	public final static UUID UUID_BAROMETER_THRESHOLD_FOR_INDICATION = UUID.fromString(YSRGattAttributes.BAROMETER_THRESHOLD_FOR_INDICATION);

	public final static UUID UUID_BAROMETER_DATA_ACCUMULATION = UUID.fromString(YSRGattAttributes.BAROMETER_DATA_ACCUMULATION);

	public final static UUID UUID_BAROMETER_READING = UUID.fromString(YSRGattAttributes.BAROMETER_READING);
	/**
	 * Accelerometer related uuid
	 */
	public final static UUID UUID_ACCELEROMETER_SERVICE = UUID.fromString(YSRGattAttributes.ACCELEROMETER_SERVICE);

	public final static UUID UUID_ACCELEROMETER_ANALOG_SENSOR = UUID.fromString(YSRGattAttributes.ACCELEROMETER_ANALOG_SENSOR);

	public final static UUID UUID_ACCELEROMETER_DATA_ACCUMULATION = UUID.fromString(YSRGattAttributes.ACCELEROMETER_DATA_ACCUMULATION);

	public final static UUID UUID_ACCELEROMETER_READING_X = UUID.fromString(YSRGattAttributes.ACCELEROMETER_READING_X);

	public final static UUID UUID_ACCELEROMETER_READING_Y = UUID.fromString(YSRGattAttributes.ACCELEROMETER_READING_Y);

	public final static UUID UUID_ACCELEROMETER_READING_Z = UUID.fromString(YSRGattAttributes.ACCELEROMETER_READING_Z);

	public final static UUID UUID_ACCELEROMETER_SENSOR_SCAN_INTERVAL = UUID.fromString(YSRGattAttributes.ACCELEROMETER_SENSOR_SCAN_INTERVAL);
	/**
	 * Analog temperature  related uuid
	 */
	public final static UUID UUID_ANALOG_TEMPERATURE_SERVICE = UUID.fromString(YSRGattAttributes.ANALOG_TEMPERATURE_SERVICE);

	public final static UUID UUID_TEMPERATURE_ANALOG_SENSOR = UUID.fromString(YSRGattAttributes.TEMPERATURE_ANALOG_SENSOR);

	public final static UUID UUID_TEMPERATURE_READING = UUID.fromString(YSRGattAttributes.TEMPERATURE_READING);

	public final static UUID UUID_TEMPERATURE_SENSOR_SCAN_INTERVAL = UUID.fromString(YSRGattAttributes.TEMPERATURE_SENSOR_SCAN_INTERVAL);

	/**
	 * RDK related UUID
	 */
	public final static UUID UUID_REP0RT = UUID.fromString(YSRGattAttributes.REP0RT);

	/**
	 * OTA related UUID
	 */
	public final static UUID UUID_OTA_UPDATE_SERVICE = UUID.fromString(YSRGattAttributes.OTA_UPDATE_SERVICE);

	public final static UUID UUID_OTA_UPDATE_CHARACTERISTIC = UUID.fromString(YSRGattAttributes.OTA_CHARACTERISTIC);

	/**
	 * Descriptor UUID
	 */
	public final static UUID UUID_CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString(YSRGattAttributes.CLIENT_CHARACTERISTIC_CONFIG);

	public final static UUID UUID_CHARACTERISTIC_EXTENDED_PROPERTIES = UUID.fromString(YSRGattAttributes.CHARACTERISTIC_EXTENDED_PROPERTIES);

	public final static UUID UUID_CHARACTERISTIC_USER_DESCRIPTION = UUID.fromString(YSRGattAttributes.CHARACTERISTIC_USER_DESCRIPTION);

	public final static UUID UUID_SERVER_CHARACTERISTIC_CONFIGURATION = UUID.fromString(YSRGattAttributes.SERVER_CHARACTERISTIC_CONFIGURATION);

	public final static UUID UUID_REPORT_REFERENCE = UUID.fromString(YSRGattAttributes.REPORT_REFERENCE);
	
	public final static UUID UUID_CHARACTERISTIC_PRESENTATION_FORMAT = UUID.fromString(YSRGattAttributes.CHARACTERISTIC_PRESENTATION_FORMAT);

}
