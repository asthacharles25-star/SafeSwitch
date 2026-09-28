# SafeSwitch

## Intelligent Forgotten Appliance Detection and Alert System

SafeSwitch is a Java-based prototype designed to detect potentially unattended appliance operation using multiple parameters such as appliance status, human presence, running time, temperature, and power consumption.

## Problem

People may accidentally leave electrical appliances switched on when leaving a room or home. Basic switches and fixed timers cannot determine whether continued appliance operation is intentional or potentially unsafe.

## Proposed Solution

SafeSwitch analyzes multiple parameters and classifies the appliance condition into different risk levels.

### Risk Levels

- **SAFE** – Appliance is OFF
- **NORMAL** – Appliance is ON and a person is present
- **MONITOR** – Appliance is ON and the person is absent
- **WARNING** – Appliance is ON, the person is absent, and the running time is long
- **HIGH RISK** – Abnormal temperature or power is detected

## How It Works

1. Receive appliance input.
2. Check whether the appliance is ON or OFF.
3. Check human presence.
4. Check running duration.
5. Check temperature and power.
6. Calculate the risk level.
7. Display the detected condition to the user.

## Technology Stack

- Java
- Java Swing
- Object-Oriented Programming
- Simulated Sensor Inputs

## Prototype Scenarios

| Scenario | Result |
|---|---|
| Normal Operation | NORMAL |
| Person Leaves Room | MONITOR |
| Forgotten Appliance | WARNING |
| Abnormal Temperature | HIGH RISK |

## Current Prototype

The current prototype uses simulated sensor inputs. Physical sensors are planned as a future implementation.

## Future Scope

- ESP32/Arduino integration
- Temperature sensor
- Current/power sensor
- Human presence sensor
- Mobile notifications
- Cloud monitoring
- Automatic safe shutdown
- Smart-home integration
- Machine-learning-based behaviour detection

## Safety Note

The current prototype uses simulated inputs and does not directly control household mains appliances.

## Project Goal

SafeSwitch aims to transform simple appliance monitoring into intelligent unattended-use detection by combining power, temperature, presence, and time-based information.
