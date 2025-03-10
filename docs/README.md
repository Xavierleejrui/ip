# Keesma User Guide


## Introduction

Keesma is a quirky and personality-filled task management application that runs on the command line. With its distinctive style and conversational interface, Keesma helps you keep track of your tasks, deadlines, and events with a uniquely entertaining approach.

## Quick Start

1. Ensure you have Java 17 installed
2. Download the latest `ip.jar` from the [releases page](https://github.com/Xavierleejrui/ip/releases)
3. Run the application using: `java -jar ip.jar`

## Features

### 1. Adding a Todo Task

Quickly add simple todo tasks to your list.

**Format:** `todo DESCRIPTION`

**Example:** `todo finish CS2113 homework`

```
____________________________________________________________
Yes bro I have added:
	[T] [ ] finish CS2113 homework
Now there are 1 tasks in the list
____________________________________________________________
```

### 2. Adding a Deadline

Add tasks with specific deadlines to ensure you never miss a due date.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Example:** `deadline submit report /by 15/3/2025 1430`

```
____________________________________________________________
Yes bro I have added:
	[D] [ ] submit report (by: Mar 15 2025, 02:30PM)
Now there are 2 tasks in the list
____________________________________________________________
```

### 3. Adding an Event

Schedule events with start and end times.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:** `event team meeting /from 16/3/2025 1000 /to 16/3/2025 1200`

```
____________________________________________________________
Yes bro I have added:
	[E] [ ] team meeting (from: Mar 16 2025, 10:00AM to: Mar 16 2025, 12:00PM)
Now there are 3 tasks in the list
____________________________________________________________
```

### 4. Listing All Tasks

View all your current tasks in a numbered list.

**Format:** `list`

```
____________________________________________________________
You have 3 tasks:
	1. [T] [ ] finish CS2113 homework
	2. [D] [ ] submit report (by: Mar 15 2025, 02:30PM)
	3. [E] [ ] team meeting (from: Mar 16 2025, 10:00AM to: Mar 16 2025, 12:00PM)
____________________________________________________________
```

### 5. Marking a Task as Done

Mark tasks as completed when you've finished them.

**Format:** `mark TASK_NUMBER`

**Example:** `mark 1`

```
____________________________________________________________
Your task has been marked as done babyboo
	[T] [X] finish CS2113 homework
____________________________________________________________
```

### 6. Unmarking a Task

Change a completed task back to incomplete if needed.

**Format:** `unmark TASK_NUMBER`

**Example:** `unmark 1`

```
____________________________________________________________
Your task has been unmarked gugu
	[T] [ ] finish CS2113 homework
____________________________________________________________
```

### 7. Deleting a Task

Remove tasks you no longer need to track.

**Format:** `delete TASK_NUMBER`

**Example:** `delete 2`

```
____________________________________________________________
Yes boss I have removed this task for you.
	[D] [ ] submit report (by: Mar 15 2025, 02:30PM)
Now there are 2 tasks in the list
____________________________________________________________
```

### 8. Finding Tasks by Keyword

Search for specific tasks using keywords.

**Format:** `find KEYWORD`

**Example:** `find meeting`

```
____________________________________________________________
Here are the matching tasks in your list for "meeting":
	1. [E] [ ] team meeting (from: Mar 16 2025, 10:00AM to: Mar 16 2025, 12:00PM)
____________________________________________________________
```

### 9. Exiting the Application

Close the application when you're done.

**Format:** `bye`

```
____________________________________________________________
Bye, Hope to see you again soon, you smell great by the way, although you did smell better last night hehe ^ W ^ 
____________________________________________________________
```

## Date and Time Format

For deadlines and events, Keesma accepts dates and times in the following format:
- `d/M/yyyy HHmm` (e.g., `15/3/2025 1430` for March 15, 2025, 2:30 PM)

Keesma will display dates in a more friendly format: `MMM d yyyy, hh:mma` (e.g., `Mar 15 2025, 02:30PM`)

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **Add Todo** | `todo DESCRIPTION` | `todo finish CS2113 homework` |
| **Add Deadline** | `deadline DESCRIPTION /by DATE_TIME` | `deadline submit report /by 15/3/2025 1430` |
| **Add Event** | `event DESCRIPTION /from START_TIME /to END_TIME` | `event team meeting /from 16/3/2025 1000 /to 16/3/2025 1200` |
| **List Tasks** | `list` | `list` |
| **Mark as Done** | `mark TASK_NUMBER` | `mark 1` |
| **Unmark** | `unmark TASK_NUMBER` | `unmark 1` |
| **Delete** | `delete TASK_NUMBER` | `delete 2` |
| **Find** | `find KEYWORD` | `find meeting` |
| **Exit** | `bye` | `bye` |

## Error Messages

Keesma will let you know when something goes wrong with its unique style:

- **Invalid Command**: "Eh bro can put a proper command anot smh"
- **Empty Todo**: "Can you type something lol thanks bro"
- **Invalid Deadline Format**: "Can you type the format properly lol thanks bro make sure you add description and date."
- **Invalid Event Format**: "Can you type the format properly lol thanks bro"
- **Invalid Task Number**: "Can type your task number properly :')"
- **Invalid Number**: "Bruh can you type a valid number? Thanks bro."
- **Empty Find Keyword**: "Bruh what you want me to find? Give keyword lah."

## Data Storage

Keesma automatically saves your tasks to a file at `data/tasks.txt`. This ensures your tasks persist between application runs.

---

*Made with ❤️ by Xavier Lee Jingrui
