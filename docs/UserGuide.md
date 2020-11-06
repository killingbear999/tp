#Cent wise Dollar wise
![image]()

## Table of Contents
1. [Introduction](#1-introduction)
2. [QuickStart](#2-quick-start)
3. [CommandFeatures](#3-command-features)

    3.1 [AddingSpending: `add`](#31-adding-spending-add)

    3.2 [ClearingData: `clear`](#32-clearing-data-clear)

    3.3 [ViewingHelp: `help`](#33-viewing-help-help)
    3.4 [PurgingData: `purge`](#34-purging-data-purge-data)
    3.5 [ViewingRepaymentListSummary: `repaymentlist`](#35-viewing-repayment-list-summary-repayment-list)
    3.6 [ViewingSpendingListSummary: `spendinglist`](#36-viewing-spending-list-summary-spending-list)
    3.7 [ViewingBudgetLimit: `view`](#37-viewing-budget-limit-view)
    3.8 [SettingBudgetLimit: `set`](#38-setting-budget-limit-set)
    3.9 [InputtingRepaymentInformation: `repay`](#39-inputting-repayment-information-repay)
    3.10 [ConvertingCurrency: `convert`](#310-converting-currency-convert)
    3.11 [ViewingSummary: `summary`](#311-viewing-summary-summary)
    3.12 [VisualizingData: `draw`](#312-visualizing-data-draw)
    3.13 [ExportingData: `export`](#313-exporting-data-export)
    3.14 [EditingSpending: `edit`](#314-editing-spending-edit)
    3.15 [ExitingProgram: `logout`](#315-exiting-program-logout)
4. [Useful Hidden Features](#4-useful-hidden-features)
    4.1 [Reminder](#41-reminder)
    4.2 [Warning](#42-warning)
    4.3 [Encouragement](#43-encouragement)
5. [FAQ](#5-faq)
6. [Command Summary](#6-command-summary)

--------------------------------------------------------------------------------------------------------------------

## **1. Introduction**
Welcome to Cent Wise Dollar Wise User Guide! Cent Wise Dollar Wise is a desktop application for money management, optimised for use via a Command Line Interface (CLI). This application aims to help NUS hostel students manage their financial situation and track their spending efficiently.
This user guide provides in-depth documentation on the application’s installation process and command features.

## **2. Quick Start**
1. Ensure that you have Java 11 or above installed in your computer.
2. Download the latest version of `Duke` from [here](https://github.com/AY2021S1-CS2113T-F14-2/tp/releases).
3. Copy the file to a home folder you would like to use.
4. Open a terminal in that home folder and enter java -jar duke.jar to begin.
5. Type the desired command and press Enter to execute it. Tips: entering help will display the help window with a list of commands that could be executed.

Refer to the [Command Features](#3-command-features) below for details of each command.

## **3. Command Features**
In this section, specific information about each command will be explained. This includes the purpose of each command, and how it is formatted.

Before we begin, do take note of the following information which applies to all commands:
* This application only takes in currencies of SGD, USD and CNY.

* Words in UPPER_CASE are parameters that you should provide.
Example: In `-c CATEGORY`, `CATEGORY` is a parameter. `-c Food` would mean that the item belongs to the 'food' category.

* Parameters in square brackets (i.e. []) are options provided for you to choose to enter (no need to enter [] when entering the command). You may choose one out of all.
Example: `edit INDEX [-c CATEGORY] [-d NEW DESCRIPTION]` can be used as `edit 1 [-c Food]` or `edit 1 [-c NEW DESCRIPTION]`

* The abbreviation of `MONTH` is case sensitive. The system only accepts the abbreviation of `MONTH` with the first three letters, and the first
letter is capitalised. Example: `Jul` for July or `Oct` for October.

* The system has the feature of a currency converter, but it only supports currency conversion for SGD to USD, or USD to SGD, or SGD to CNY, or CNY to SGD.

* The default currency of the system is SGD, but after calling `convert` feature, the default currency will be changed to the currency you have converted to.

* There are 6 main categories of spending items:
    1. Education
    2. Entertainment
    3. Food
    4. Health
    5. Transportation
    6. Utilities

Note: If you categorise an item in a category that is not from these six, the item will be categorised as Others.

### 3.1 Adding Spending: `add`
This command allows you to add a spending record to the application.

Format:

`add -c CATEGORY -d DESCRIPTION -s CURRENCY SPENDING`

Example of usage:

`add -c Food -d chicken rice -s SGD 3.00` → adds chicken rice which costs SGD 3.00 into the food category of the spending list

Figure below shows an example for Adding Spending Feature: <br>

![image](https://user-images.githubusercontent.com/45732128/98068206-6302fd00-1e96-11eb-9f94-af0daeed3175.png)
🛈 The date shown in the UI output refers to the date when an entry is recorded.

### 3.2 Clearing Data: `clear`
The `clear` command allows you to clear specific data that you have stored in the application. This includes your spending list, repayment list and budget. You can choose to clear all entries or specified entries of either your spending list or repayment list (or both).

🛈 If you do not specify SPENDING_INDEX or REPAYMENT_INDEX but specify its corresponding prefix, the whole list representing it will be cleared.

Format:

`clear [-b] [-s SPENDING_INDEX] [-r REPAYMENT_INDEX]`

Example of usage:

`clear -b` → clears the budget limit set previously

`clear -s 1` → clears entry 1 of the spending list

`clear -r 1` → clears entry 1 of the repayment list

`clear -s` → clears the entire spending list

`clear -r` → clears the entire repayment list

Figure below shows an example for Clearing Data feature: <br>

![image](https://user-images.githubusercontent.com/45732128/98068110-1b7c7100-1e96-11eb-9bc8-82e839885d6a.png)

### 3.3 Viewing Help: `help`
If you are unsure on how to use a command, or would like to know more uses of a command, you can use this command to get more information.

Format:

`help`

You shall expect to see the following help screen: <br>

![image](https://user-images.githubusercontent.com/45732128/98068989-a8c0c500-1e98-11eb-9b1e-efbd6a1d7b7b.png)

### 3.4. Purging Data: `purge data`
This command is similar to the `clear` command, except that it allows you to clear all data, including your spending list, repayment list and budget, all at one go.

Format:

`purge data`

### 3.5 Viewing Repayment List Summary: `repayment list`
This command shows your repayment list.

Format:

`repayment list`

### 3.6 Viewing Spending List Summary: `spending list`

This command shows your spending records during a specified period (a particular year or month, or both).
You can also choose to view your spending records which belong to a specific spending category.

Format:

`spending list [YEAR] [MONTH] [-c CATEGORY] [-a]`

Examples of usage:

`spending list` → lists all entries for the current month

`spending list 2020` → lists all entries for year 2020

`spending list 2020 Jul` → lists all entries for July 2020

`spending list -c Food` → lists all food entries for the current month

`spending list 2020 -c Food` → lists all food entries for the year 2020

`spending list 2020 Jul -c Food` → lists all food entries for July 2020

`spending list -a` → lists all entries

### 3.7 Viewing Budget Limit: `view`
This command shows the current budget limit you have set.

Format:

`view`

Example of usage:

`view` → views the budget limit that was set previously

Figure below shows an example for Viewing Budget Limit feature (i.e. budget limit has not been set yet): <br>

![Example for Viewing Budget Limit](images/NoBudgetExample.png)

Figure below shows an example for Viewing Budget Limit feature (i.e. budget limit has been set): <br>

![Example for Viewing Budget Limit](images/BudgetExample.png)

### 3.8 Setting Budget Limit: `set`
This command allows you to set a budget limit. You will receive a Ui warning message when your spending approaches
the limit or exceeds the limit.

Format:

`set -s CURRENCY AMOUNT`

Example of usage:

`set -s SGD 100.0` → sets the budget limit to SGD 100.0

### 3.9 Inputting Repayment Information: `repay`
This command adds a repayment record to your repayment list.

Format:

`repay -n NAME -s CURRENCY AMOUNT -t DEADLINE`

Example of usage:

`repay -n Johnny -s SGD 5.0 -t 2020-12-02` → stores the information that you need to repay SGD 5.0 to Johnny before
2020-12-02

Figure below shows an example for Inputting Repayment Information feature: <br>

![Example for Inputting Repayment Information](images/RepayExample.png)

### 3.10 Converting Currency: `convert`
This command converts the currency in your spending list to another currency you prefer.

Format:

`convert -s INPUT_CURRENCY -t OUTPUT_CURRENCY`

Example of usage:

`convert -s SGD -t USD` → converts the currency from SGD to USD

Figure below shows an example for Converting Currency feature: <br>

![Example for Converting Currency](images/ConvertExample.png)

### 3.11 Viewing Summary: `summary`
This command generates a statement of your total expenditure during a specific period of time (a particular year or
month, or both). Statements based on each spending category will also be shown.

Format:

`summary [YEAR] [MONTH] [-a]`

Examples of usage:

`summary` → shows summary of current month

`summary 2020` → shows summary of a specific year

`summary 2020 Oct` → shows summary of a specific year and month

`summary -a` → shows the summary of total expenditure

### 3.12 Visualizing Data: `draw`

This command generates graphs showing your spending records of a particular year or month. The graphs will be in an Excel file at the same folder of this application.

Format:

`draw [YEAR] [MONTH]`

Examples of usage:

`draw` → shows the graph of current month

`draw 2012` → shows the graph of year 2012

`draw 2020 Jun` → shows the graph of Jun 2020

### 3.13 Exporting Data: `export`

This command exports the data to an Excel file at the given location.

Format:

`export PATH`

Example of usage:

`export F:\MyFolder exports` → the records to an Excel file located at F:\MyFolder

### 3.14 Editing Spending: `edit`
This command edits the existing records in the spending list.

Format:

`edit INDEX [-c CATEGORY] [-d NEW DESCRIPTION] [-s CURRENCY NEW SPENDING]`

Example of usage:

`edit 3 -s SGD 4.0` → edits the amount in the first item in the spending list to SGD 4.0

Figure below shows an example for Editing Spending feature: <br>

![Example for Editing Spending](images/EditExample.png)

### 3.15 Exiting Program: `logout`
This command exists the application.

Format:

`logout`

Example of usage:

`logout` → exits the program

## **4. Useful Hidden Features**
This section shows you the hidden features which serve as notifications and interactions with the user.
### 4.1 Reminder
Reminds you about your weekly expenditures, and the amount of budget left when you start the application.
It will also warn you if you are reaching your budget limit. <br>

![reminder message](images/reminderMessage.png)

Note: A new week starts on Monday.

### 4.2 Warning
Warns you when you are about to overspend. The budget threshold is 90% of your budget set. After you add a spending
item and the total expenditure is more than the budget threshold, warning message will appear. <br>

![reminder message](images/warningMessage.png)

Note: You need to set the budget in order to get the warning feature.

### 4.3 Encouragement
Being able to create your budget is a great start, but if you do not have the willpower to stick to the budget you have set, you will soon discover that using this application is a waste of your time. Hence, this feature is implemented to motivate you in keeping up your efforts in budgeting. An encouragement message will be shown every eight times you add an item to the spending list.

Figure below shows an example of encouragement message. <br>

![image](https://user-images.githubusercontent.com/45732128/97827031-108ed880-1cfe-11eb-8071-fb5dc906b216.png)

## **5. FAQ**

**Q**: How do I transfer my data to another computer?

**A**: Install the app in the other computer and copy the data/duke.json to the same folder. When you start the app, it will automatically detect and load the backup data.

**Q**: Are the commands case sensitive?

**A**: Yes, they are. To execute the commands properly, please strictly follow the format given above.

## **6. Command Summary**

| Action | Format | Examples |
|:------:|--------|----------|
|add | `add  -c CATEGORY -d DESCRIPTION -s CURRENCY SPENDING` | add -c Food -d chicken rice -s SGD 5.0|
|clear | `clear [-b] [-s SPENDING_INDEX] [-r REPAYMENT_INDEX]` | clear -s 1|
|convert | `convert -s INPUT_CURRENCY -t OUTPUT_CURRENCY` | convert -s SGD -t USD|
|draw | `draw [YEAR] [MONTH]`|
|edit | `edit INDEX [-c CATEGORY] [-d NEW_DESCRIPTION] [-s CURRENCY NEW_SPENDING]` | edit 1 -d bubble tea|
|export | `export PATH`|
|help | `help`|
|logout | `logout`|
|purge data | `purge data`|
|repay | `repay -d NAME -s CURRENCY AMOUNT -t DEADLINE` | repay -d Johnny -s SGD 5.00 -t 2020-12-02|
|repayment list | `repayment list`|
|set | `set -s CURRENCY AMOUNT` | set -s SGD 100.00|
|spending list | `spending list [YEAR] [MONTH] [-c CATEGORY] [-a]` | spending list 2020 Jul|
|summary | `summary [YEAR] [MONTH] [-a]` | summary 2020 Jul |
|view | `view`|
