# TASKFLOW - PRESENTATION SLIDES
## Mobile Application Development | IC 2305 | Assignment Part 1 (UI Design)

**Index Number:** 2021T01222  
**Student Name:** Mohammed Muaaz  
**Department of ICT, Faculty of Technology, University of Colombo**

---

# SLIDE 1: TITLE PAGE

```
╔═══════════════════════════════════════════════════════════════╗
║                                                               ║
║         Mobile Application Development                        ║
║         IC 2305                                               ║
║                                                               ║
║         Assignment: Part 1 (UI Design)                        ║
║                                                               ║
║         ┌─────────────────────────────────┐                   ║
║         │      TaskFlow                   │                   ║
║         │   ✓ (checkmark) + flow design   │                   ║
║         │   Dark Theme • Gold Accents     │                   ║
║         └─────────────────────────────────┘                   ║
║                                                               ║
║         Index Number: 2021T01222                              ║
║         Student Name: Mohammed Muaaz                          ║
║         Department of ICT, Faculty of Technology              ║
║         University of Colombo                                 ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝
```

---

# SLIDE 2: INTRODUCTION & METHODOLOGY

## Introduction

**Project Name:** TaskFlow  
**Tagline:** "Your Tasks, Your Flow"

**Core Purpose:**  
TaskFlow is a productivity-focused Android application designed to help users efficiently manage their daily tasks, schedule time effectively through an integrated calendar, and maintain deep focus using a built-in Pomodoro timer (Focus Mode).

**Target Audience:**
- Students juggling multiple assignments
- Professionals managing complex workflows
- Anyone seeking a streamlined, minimalist tool to organize their work

**Key Features:**
- ✅ Task Management (Create, Read, Update, Delete)
- 📅 Visual Calendar with task indicators
- ⏱️ Focus Mode (Pomodoro Timer - 25:00 timer)
- 🔐 Secure User Authentication
- 👤 User Profile & Developer Info screens
- 🎨 Dark theme optimized for productivity

---

## Methodology

**Development Model:**  
Agile/Iterative Development with continuous refinement of UI/UX based on visual feedback and user testing.

**Technology Stack:**
- **Language:** Kotlin (modern, safe, concise)
- **Architecture:** MVVM (Model-View-ViewModel)
- **UI Framework:** 
  - XML Layouts (robust, structured Activity designs)
  - Jetpack Compose (dynamic, reusable components)
- **Database:** Room Database (local-first, offline reliability)
- **Security:** AES Encryption (AndroidX Security) for credential protection
- **Dependency Injection:** Hilt (Dagger) for component lifecycle management
- **Async Processing:** Kotlin Coroutines & Flow for non-blocking operations

**Design Process:**
1. Wireframing & User Flow Analysis
2. Visual Design System Creation
3. High-Fidelity Prototype Development (with design tokens)
4. Interactive Prototype Testing
5. Development Handoff with detailed specs

---

# SLIDE 3: SYSTEM DESIGN (ARCHITECTURE)

## Architectural Pattern: MVVM

```
┌──────────────────────────────────────────────────────┐
│                    VIEW LAYER                        │
│  (Activities, Fragments, Composables)                │
└────────────────┬─────────────────────────────────────┘
                 │ (observes)
┌────────────────▼─────────────────────────────────────┐
│              VIEWMODEL LAYER                         │
│  • UI State Management                               │
│  • Business Logic Orchestration                      │
│  • Repository Communication                          │
└────────────────┬─────────────────────────────────────┘
                 │ (calls)
┌────────────────▼─────────────────────────────────────┐
│            REPOSITORY LAYER                          │
│  • AuthRepository (authentication)                   │
│  • TaskRepository (task CRUD)                        │
│  • UserRepository (profile management)               │
└────────────────┬─────────────────────────────────────┘
                 │ (queries)
┌────────────────▼─────────────────────────────────────┐
│         MODEL LAYER (Room Database)                  │
│  • User Entity                                       │
│  • Task Entity                                       │
│  • DeveloperInfo Entity                              │
└──────────────────────────────────────────────────────┘
```

## Key Components

**Model Layer:**
- Room entities representing Tasks, Users, and Developer Info
- DAOs (Data Access Objects) for database queries
- Type-safe, compile-time verified queries

**View Layer:**
- Activities for navigation flow (Splash → Auth → Main App)
- Fragment-based screens for modularity
- Material Design 3 compliance for consistency

**ViewModel Layer:**
- Maintains UI state across configuration changes
- Communicates with repositories
- Exposes data via StateFlow and LiveData

**Dependency Injection (Hilt):**
- Centralized component management
- Automatic lifecycle-aware injection
- Modules for database, repositories, utilities

**Asynchronous Processing:**
- Coroutines for background operations
- Flow for real-time database updates
- StateFlow for reactive UI state management

---

# SLIDE 4: USER INTERFACE (UI) DESIGN

## Design Philosophy

**Core Principle:** "Clean, High-Contrast, Professional"

A minimalist dark theme optimized for extended screen time and focus. The UI prioritizes clarity, accessibility, and user productivity through intelligent use of whitespace and hierarchy.

## Key UI Components

**1. Dashboard/Home Screen**
- Quick-access profile widget with user avatar
- Greeting based on time of day ("Good Morning", "Good Evening")
- Today's date display with calendar integration
- Main task list with scroll support
- Visual indicators: checkmarks for completion, edit/delete icons

**2. Material Calendar**
- Full month view (May 2026 by default)
- Task indicators on dates (colored dots)
- Click-to-navigate to daily tasks
- Navigation arrows for month switching
- Color-coded task priorities (optional)

**3. Focus Timer Screen**
- Centered countdown display (25:00 Pomodoro default)
- Large, readable digits in monospace font
- START and RESET buttons
- Minimalist design to reduce cognitive load
- Optional break timer notifications

**4. Task Management Interface**
- List view with swipe actions (edit/delete)
- Modal dialog for creating new tasks
- Fields: Title, Description, Due Date
- Quick completion toggle with checkbox
- Edit functionality with inline updates

**5. Authentication Screens**
- Sign In: Username + Password (with "Forgot Password" option)
- Sign Up: Username + Email + Password + Confirm Password
- Terms & Conditions checkbox
- Smooth transition animations

**6. User Profile Screen**
- Profile avatar (circular frame)
- Username and email display
- Edit Info button (launches modal)
- Sign Out confirmation dialog
- Delete Account option

**7. Developer Info Screen**
- Name, Student Number, Email display
- Personal Statement (scrollable if long)
- Release Version (monospace)
- Exit button for navigation

**8. Adaptive Bottom Navigation**
- 5 icons: Home, Schedule, Focus, Calendar, Profile
- Active state highlighting with gold accent
- Single-tap navigation between sections
- Persistent across most screens

---

# SLIDE 5: TYPOGRAPHY SYSTEM

## Font Families

**Primary Font Family:** System Default / Sans-Serif
- High readability on all screen sizes
- Consistent with Material Design 3
- Optimized for both body text and UI labels

**Secondary Font Family:** Monospace
- Used for: Focus Timer digits, version numbers, student ID
- Ensures character alignment and precise spacing
- Improves readability of numeric data

## Typography Hierarchy

| Level | Size | Weight | Use Case |
|-------|------|--------|----------|
| Display | 32sp | Bold | App Title ("TaskFlow"), Welcome screens |
| Headline | 24sp | Bold | Screen titles ("My Tasks", "Create a Task") |
| Title | 20sp | Bold | Section headers, card titles |
| Body Large | 16sp | Normal | Main content, task descriptions |
| Body | 14sp | Normal | Standard text, labels |
| Label | 12sp | Medium | Buttons, badges, hints |
| Caption | 10sp | Regular | Timestamps, secondary info |

## Example Usage

- **Display (32sp Bold):** "TaskFlow" on splash screen
- **Title (20sp Bold):** "My Tasks" header on home screen
- **Body (14sp Normal):** Task list item descriptions
- **Label (12sp Medium):** Button text ("SIGN IN", "CREATE ACCOUNT")
- **Caption (10sp Regular):** "17 May" date labels, task timestamps

---

# SLIDE 6: COLOR PALETTE

## Color System Overview

```
┌─────────────────────────────────────────────────────┐
│  BACKGROUND LAYER                                   │
│  Primary Background:     #1A1A1A (Deep Charcoal)    │
│  Secondary Background:   #2D2D2D (Card Surface)     │
│  Purpose: Reduces eye strain, dark theme optimized  │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│  BRAND COLORS (Interactive Elements)                │
│  Primary Action:         #2D5BA8 (Professional Blue)│
│  Brand Accent:           #D4A574 (Warm Gold)        │
│  Purpose: High-visibility CTA, icons, highlights    │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│  TEXT LAYER (Readability)                           │
│  Text Primary:           #FFFFFF (Pure White)       │
│  Text Secondary:         #B3FFFFFF (70% White)      │
│  Purpose: Titles vs. subtitles, semantic contrast   │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│  SEMANTIC COLORS (Priority/Status)                  │
│  Urgent (High Priority):     #E74C3C (Red)          │
│  Completed/Success:          #27AE60 (Green)        │
│  Work (Medium Priority):     #3498DB (Blue)         │
│  Low Priority:               #F1C40F (Yellow/Gold)  │
└─────────────────────────────────────────────────────┘
```

## Color Details

### Background Colors
- **#1A1A1A (Deep Charcoal)**
  - Primary background for all screens
  - Reduces blue light emission for extended use
  - WCAG AAA contrast compliant with text

- **#2D2D2D (Slightly Lighter Grey)**
  - Used for cards, input fields, task list items
  - Creates visual depth and hierarchy
  - Distinguishes interactive zones from static areas

### Brand Colors
- **#2D5BA8 (Professional Blue)**
  - Sign In / Sign Up buttons
  - Navigation active states
  - Primary CTAs (Call-To-Action)
  - "EXIT" button on Dev Info screen
  - Conveys trust and professionalism

- **#D4A574 (Warm Gold)**
  - App logo (checkmark + flow design)
  - Bottom navigation active icon
  - Today's date circle on calendar
  - Focus timer START button
  - Form field labels and hints
  - Creates visual warmth and focus guidance

### Text Colors
- **#FFFFFF (Pure White)**
  - Titles, important text
  - High contrast for readability
  - Headers and primary information

- **#B3FFFFFF (70% Opacity White)**
  - Subtitles, secondary text
  - Hints and placeholders
  - Less prominent information
  - Reduces visual noise

### Semantic Colors
- **#E74C3C (Red)** - Destructive actions (Delete), errors
- **#27AE60 (Green)** - Success states, completed tasks
- **#3498DB (Blue)** - Work category, task type indicator
- **#F1C40F (Yellow)** - Warnings, low priority tasks

## Color Accessibility

- Contrast Ratio: Minimum 4.5:1 (WCAG AA)
- High contrast ensures readability for:
  - Users with color blindness
  - Low-light environments
  - Extended screen viewing sessions
- Semantic colors supplement (not replace) text labels

---

# SLIDE 7: LAYOUTS - AUTHENTICATION SCREENS

## Splash Screen
```
┌──────────────────────────────────┐
│  12:28                  ◀ ⫿ ▶ ◀  │
├──────────────────────────────────┤
│                                  │
│                                  │
│          ┌─────────────┐         │
│          │  TaskFlow   │         │
│          │   (logo)    │         │
│          └─────────────┘         │
│                                  │
│        TaskFlow                  │
│       "Your Tasks,               │
│        Your Flow"                │
│                                  │
│         ⟲ (Loading)              │
│                                  │
│                                  │
│                                  │
└──────────────────────────────────┘
```

**Duration:** 4 seconds (with progress loader)
**Next Screen:** Sign In (if logged in) or Auth flow (if new user)

---

## Sign Up Screen
```
┌──────────────────────────────────┐
│  12:32              ⟲ ◀ ⫿ ▶ ◀   │
├──────────────────────────────────┤
│                                  │
│        Create Account            │
│      Join TaskFlow today         │
│                                  │
│  Username (label)                │
│  ┌──────────────────────────────┐│
│  │ Choose username        (icon) ││
│  └──────────────────────────────┘│
│                                  │
│  Email Address (label)           │
│  ┌──────────────────────────────┐│
│  │ your.email@example.com (icon) ││
│  └──────────────────────────────┘│
│                                  │
│  Password (label)                │
│  ┌──────────────────────────────┐│
│  │ Min 6 characters       (icon) ││
│  └──────────────────────────────┘│
│  Must have uppercase, lowercase, │
│  number                          │
│                                  │
│  Confirm Password (label)        │
│  ┌──────────────────────────────┐│
│  │ Re-enter password      (icon) ││
│  └──────────────────────────────┘│
│                                  │
│  ☐ I agree to Terms & Conditions │
│                                  │
│ ┌──────────────────────────────┐ │
│ │   CREATE ACCOUNT             │ │
│ └──────────────────────────────┘ │
│                                  │
│   Already have an account?       │
│        ▶ SIGN IN ◀              │
│                                  │
└──────────────────────────────────┘
```

**Key Elements:**
- Username validation: 3-20 alphanumeric + underscore
- Email validation: Standard email format
- Password strength: Min 6 chars, uppercase + lowercase + number
- Terms checkbox (required)
- Link to Sign In for existing users

---

## Sign In Screen
```
┌──────────────────────────────────┐
│  12:31                  ◀ ⫿ ▶ ◀  │
├──────────────────────────────────┤
│                                  │
│       Welcome Back               │
│     Sign in to your account      │
│                                  │
│  Username (label)                │
│  ┌──────────────────────────────┐│
│  │ Enter your username    (icon) ││
│  └──────────────────────────────┘│
│                                  │
│  Password (label)                │
│  ┌──────────────────────────────┐│
│  │ Enter your password    (👁 )  ││
│  └──────────────────────────────┘│
│                                  │
│  ☐ Remember Password  Forgot Pwd │
│                                  │
│ ┌──────────────────────────────┐ │
│ │      SIGN IN                 │ │
│ └──────────────────────────────┘ │
│                                  │
│            Forgot Password?      │
│                                  │
│  Don't have an account?          │
│        ▶ SIGN UP ◀              │
│                                  │
└──────────────────────────────────┘
```

**Key Elements:**
- Username/Email field with icon
- Password field with toggle visibility (eye icon)
- "Remember Password" checkbox
- "Forgot Password?" link
- Sign In button (primary blue)
- Link to Sign Up for new users

---

# SLIDE 8: LAYOUTS - MAIN APP SCREENS

## Task Screen (My Tasks)
```
┌──────────────────────────────────┐
│  12:30    ☰ Profile              │
├──────────────────────────────────┤
│ ○ Hello, Ayman          12 Thu   │
│                                  │
│ ⏰ Good, Morning                  │
│    [illustration]                │
│                                  │
│ Task List for Today              │
│ ┌──────────────────────────────┐ │
│ │ ☑ Drink Water      ✎ ✕       │ │
│ ├──────────────────────────────┤ │
│ │ ☑ Drink Water      ✎ ✕       │ │
│ ├──────────────────────────────┤ │
│ │ ☐ Need personal works  ✎ ✕   │ │
│ └──────────────────────────────┘ │
│                                  │
│                             ⊕    │
├──────────────────────────────────┤
│ 🏠  📅  ⊕  ⏱  👤                 │
│ Home Sched Focus Focus Profile    │
└──────────────────────────────────┘
```

**Key Elements:**
- Profile widget with avatar and date
- Time-based greeting ("Good Morning")
- Task list with completion checkboxes
- Edit (✎) and delete (✕) icons per task
- FAB for adding new task
- Bottom navigation (5 sections)

---

## Create Task Screen
```
┌──────────────────────────────────┐
│  12:31                ☰ Profile   │
├──────────────────────────────────┤
│     May 2026                     │
│  [calendar header]               │
│                                  │
│  Create New Task                 │
│                                  │
│  Task Title (label, gold)        │
│  ┌──────────────────────────────┐│
│  │ What needs to be done?       ││
│  └──────────────────────────────┘│
│                                  │
│  Description (label, gold)       │
│  ┌──────────────────────────────┐│
│  │ Add more details...          ││
│  │                              ││
│  │                              ││
│  └──────────────────────────────┘│
│                                  │
│  Choose Date (label)             │
│  ┌──────────────────────────────┐│
│  │ Date                    ▼    ││
│  └──────────────────────────────┘│
│                                  │
│ ┌──────────┐      ┌────────────┐ │
│ │ CANCEL   │      │ CREATE     │ │
│ └──────────┘      └────────────┘ │
│                                  │
└──────────────────────────────────┘
```

**Key Elements:**
- Modal dialog (semi-transparent overlay)
- Task Title input (required)
- Description textarea (optional)
- Date picker dropdown
- Cancel and Create buttons
- Clear visual hierarchy with gold labels

---

## Calendar Screen
```
┌──────────────────────────────────┐
│  12:30    ☰ Profile              │
├──────────────────────────────────┤
│        Calendar                  │
│       May 2026                   │
│                                  │
│  ◀ May 2026 ▶                    │
│                                  │
│ Sun Mon Tue Wed Thu Fri Sat       │
│  1   2   3   4   5   6           │
│  7   8   9  10  11  12  13       │
│ 14  15  16  17◉ 18  19  20       │
│ 21  22  23  24  25  26  27       │
│ 28  29  30  31                   │
│                                  │
│ Sunday, May 17                   │
│ ┌──────────────────────────────┐ │
│ │ ☐ Self Learning    17 May ✎ ✕ │ │
│ └──────────────────────────────┘ │
│                                  │
├──────────────────────────────────┤
│ 🏠  📅  ⊕  ⏱  👤                 │
└──────────────────────────────────┘
```

**Key Elements:**
- Full month view (May 2026)
- Today's date circled (17)
- Task dots on dates
- Month navigation arrows
- Selected date tasks listed below
- Swipeable task list for daily view

---

## Focus Screen (Pomodoro Timer)
```
┌──────────────────────────────────┐
│  12:30    ☰ Profile              │
├──────────────────────────────────┤
│                                  │
│      STAY FOCUSED                │
│                                  │
│                                  │
│          25:00                   │
│       (monospace font)           │
│                                  │
│                                  │
│ ┌──────────────────────────────┐ │
│ │        START                 │ │
│ └──────────────────────────────┘ │
│                                  │
│ ┌──────────────────────────────┐ │
│ │        RESET                 │ │
│ └──────────────────────────────┘ │
│                                  │
│                                  │
├──────────────────────────────────┤
│ 🏠  📅  ⊕  ⏱  👤                 │
└──────────────────────────────────┘
```

**Key Elements:**
- Centered timer display (monospace)
- "STAY FOCUSED" motivational header
- START button (gold, large tap target)
- RESET button (secondary action)
- Minimal UI to reduce distractions
- Works as 25-minute Pomodoro timer

---

## User Info Screen
```
┌──────────────────────────────────┐
│  12:53    ☰ Profile              │
├──────────────────────────────────┤
│                                  │
│          ○ (avatar)              │
│                                  │
│        mazuaqw                   │
│      email@gmail.com             │
│                                  │
│ ┌──────────────────────────────┐ │
│ │      EDIT INFO               │ │
│ └──────────────────────────────┘ │
│                                  │
│ ┌──────────────────────────────┐ │
│ │      SIGN OUT                │ │
│ └──────────────────────────────┘ │
│                                  │
│ ┌──────────────────────────────┐ │
│ │   Delete Account             │ │
│ │     (link, red text)         │ │
│ └──────────────────────────────┘ │
│                                  │
├──────────────────────────────────┤
│ 🏠  📅  ⊕  ⏱  👤                 │
└──────────────────────────────────┘
```

**Key Elements:**
- Large avatar circle (profile picture)
- Username and email display
- Edit Info button (opens modal)
- Sign Out button (primary blue)
- Delete Account link (red, destructive)

---

## Edit User Info Modal
```
┌──────────────────────────────────┐
│  12:31    ☰ Profile              │
├──────────────────────────────────┤
│                                  │
│  ┌────────────────────────────┐  │
│  │                            │  │
│  │   Edit User Info           │  │
│  │                            │  │
│  │ ┌──────────────────────┐   │  │
│  │ │ Username             │   │  │
│  │ │ mazuaqw        (icon)│   │  │
│  │ └──────────────────────┘   │  │
│  │                            │  │
│  │ ┌──────────────────────┐   │  │
│  │ │ Email                │   │  │
│  │ │ itz@gmail.com  (icon)│   │  │
│  │ └──────────────────────┘   │  │
│  │                            │  │
│  │ ┌──────────┐  ┌──────────┐ │  │
│  │ │ OK       │  │ CANCEL   │ │  │
│  │ └──────────┘  └──────────┘ │  │
│  │                            │  │
│  └────────────────────────────┘  │
│                                  │
└──────────────────────────────────┘
```

**Key Elements:**
- Modal dialog overlay
- Username editable field
- Email editable field
- OK button (save changes, primary blue)
- CANCEL button (secondary)
- Form validation on submit

---

## Developer Info Screen
```
┌──────────────────────────────────┐
│  12:31    ⟨ (back arrow)         │
├──────────────────────────────────┤
│                                  │
│ ┌──────────────────────────────┐ │
│ │ Name:                        │ │
│ │ Muaaz                        │ │
│ │                              │ │
│ │ Student No.:                 │ │
│ │ 2021T01222                   │ │
│ │                              │ │
│ │ Email:                       │ │
│ │ 2021t01222@stu.cmb.ac.lk    │ │
│ │                              │ │
│ │ Personal Statement:          │ │
│ │ MAD Assignment               │ │
│ │                              │ │
│ │ Release Version:             │ │
│ │ 1.0.0                        │ │
│ └──────────────────────────────┘ │
│                                  │
│ ┌──────────────────────────────┐ │
│ │       EXIT                   │ │
│ └──────────────────────────────┘ │
│                                  │
└──────────────────────────────────┘
```

**Key Elements:**
- Back arrow navigation
- Name field
- Student Number (2021T01222)
- Email (university email)
- Personal Statement text
- Release Version (v1.0.0)
- EXIT button (primary blue, navigation)

---

# SLIDE 9: SIGN OUT CONFIRMATION MODAL

```
┌──────────────────────────────────┐
│  12:53    ☰ Profile              │
├──────────────────────────────────┤
│                                  │
│  ┌────────────────────────────┐  │
│  │                            │  │
│  │  Really Want to Sign out?  │  │
│  │  (with overlay)            │  │
│  │                            │  │
│  │  ┌──────────┐  ┌─────────┐ │  │
│  │  │ OK       │  │ CANCEL  │ │  │
│  │  └──────────┘  └─────────┘ │  │
│  │                            │  │
│  │  Username: Ayman           │  │
│  │  Email: aymn@email.com     │  │
│  │                            │  │
│  │  ┌──────────────────────┐  │  │
│  │  │   EDIT INFO          │  │  │
│  │  └──────────────────────┘  │  │
│  │                            │  │
│  │  ┌──────────────────────┐  │  │
│  │  │   SIGN OUT           │  │  │
│  │  └──────────────────────┘  │  │
│  └────────────────────────────┘  │
│                                  │
└──────────────────────────────────┘
```

**Trigger:** User taps "Sign Out" button on User Info screen
**Actions:**
- OK: Proceed with sign out (clears session, returns to Sign In)
- CANCEL: Dismiss modal, return to User Info

---

# SLIDE 10: DIMENSIONS & SPECIFICATIONS

## Screen Dimensions

```
Device: Standard Android Mobile
┌─────────────────────┐
│ 393 dp width        │
│ 852 dp height       │
│ (Aspect: 9:19.5)    │
└─────────────────────┘
```

## Component Specifications

### Status Bar & Header
- Height: 42 dp (status bar)
- Safe area padding: 16 dp (horizontal)

### Input Fields
- Height: 56 dp
- Corner radius: 8 dp
- Padding: 12 dp (internal)
- Border: 1 dp (0.5 dp opacity on #FFFFFF)

### Buttons
- Height: 48 dp
- Corner radius: 28 dp (pill-shaped)
- Minimum width: 120 dp
- Text size: 14sp, Bold
- Padding: 12 dp vertical, 24 dp horizontal

### FAB (Floating Action Button)
- Size: 56 dp × 56 dp
- Margin from screen edge: 16 dp
- Icon size: 24 dp
- Corner radius: 50% (circular)

### Bottom Navigation
- Height: 60 dp
- Icon size: 24 dp
- Label text size: 12sp
- Padding: 8 dp top/bottom

### Cards & List Items
- Corner radius: 8 dp
- Padding: 12-16 dp
- Shadow elevation: 2-4 dp
- Item height: 64 dp (task list items)

### Typography Sizing

| Element | Size | Weight |
|---------|------|--------|
| App Title | 32sp | Bold |
| Screen Title | 24sp | Bold |
| Section Header | 20sp | Semi-Bold |
| Body Text | 14sp | Regular |
| Button Label | 12sp | Bold |
| Hint Text | 12sp | Regular |
| Caption | 10sp | Regular |

## Color Specification

### Background Colors
- Primary: #1A1A1A (RGB: 26, 26, 26)
- Surface: #2D2D2D (RGB: 45, 45, 45)
- Divider: #20FFFFFF (20% white overlay)

### Brand Colors
- Primary Blue: #2D5BA8 (RGB: 45, 91, 168)
- Gold Accent: #D4A574 (RGB: 212, 165, 116)

### Text Colors
- Primary: #FFFFFF (RGB: 255, 255, 255)
- Secondary: #B3FFFFFF (70% white)
- Tertiary: #80FFFFFF (50% white)

### Semantic Colors
- Error: #E74C3C (RGB: 231, 76, 60)
- Success: #27AE60 (RGB: 39, 174, 96)
- Warning: #F1C40F (RGB: 241, 196, 15)
- Info: #3498DB (RGB: 52, 152, 219)

---

# SLIDE 11: INTERACTIVE STATES & TRANSITIONS

## Button States

### Primary Button (Sign In, Create Account)
```
DEFAULT:
┌─────────────────────────┐
│   SIGN IN               │ (Background: #2D5BA8, Text: #FFFFFF)
└─────────────────────────┘

HOVER/FOCUS:
┌─────────────────────────┐
│   SIGN IN               │ (Background: #1E4279, shadow +2dp)
└─────────────────────────┘

PRESSED:
┌─────────────────────────┐
│   SIGN IN               │ (Background: #1A3956, scale: 0.98)
└─────────────────────────┘

DISABLED:
┌─────────────────────────┐
│   SIGN IN               │ (Background: #666666, opacity: 0.6)
└─────────────────────────┘
```

### Secondary Button (Cancel, Reset)
```
DEFAULT:
┌─────────────────────────┐
│   CANCEL                │ (Background: #3A3A3A, Text: #FFFFFF)
└─────────────────────────┘

HOVER/FOCUS:
┌─────────────────────────┐
│   CANCEL                │ (Background: #4A4A4A, shadow +2dp)
└─────────────────────────┘

PRESSED:
┌─────────────────────────┐
│   CANCEL                │ (Background: #505050, scale: 0.98)
└─────────────────────────┘
```

## Icon States

### Task Checkbox
```
UNCHECKED:
☐ (Border: #FFFFFF, 2dp stroke)

CHECKED:
☑ (Background: #2D5BA8, checkmark: #FFFFFF)

DISABLED:
☐ (Border: #666666, 1dp stroke, opacity: 0.6)
```

### Bottom Navigation Icons
```
INACTIVE:
Icon: #FFFFFF at 24dp

ACTIVE:
Icon: #D4A574 at 24dp
Label: #D4A574 at 12sp
Background pulse: subtle animation
```

## Input Field States

### Text Input
```
DEFAULT:
┌─────────────────────────┐
│ Enter your username     │ (Border: #3A3A3A, 1dp)
└─────────────────────────┘

FOCUSED:
┌─────────────────────────┐
│ Enter your username     │ (Border: #D4A574, 2dp)
│ (cursor blinking)       │
└─────────────────────────┘

ERROR:
┌─────────────────────────┐
│ Enter your username     │ (Border: #E74C3C, 2dp)
│ ✕ Username required     │ (Helper text: #E74C3C)
└─────────────────────────┘

FILLED:
┌─────────────────────────┐
│ mazuaqw                 │ (Text: #FFFFFF, background: #1A1A1A)
└─────────────────────────┘
```

## Animation Specifications

| Transition | Duration | Easing | Use Case |
|-----------|----------|--------|----------|
| Button Press | 200ms | ease-out | Click feedback |
| Screen Transition | 300ms | ease-in-out | Navigation |
| Modal Open | 250ms | cubic-bezier(0.4, 0, 0.2, 1) | Dialog entry |
| Checkbox Check | 150ms | ease-out | Task completion |
| FAB Rotation | 300ms | ease-in-out | FAB state change |
| Snackbar Slide | 250ms | ease-out | Toast notification |

---

# SLIDE 12: ACCESSIBILITY & USABILITY

## Accessibility Features

### Touch Targets
- Minimum size: 48dp × 48dp
- Spacing: 8dp between interactive elements
- Compliant with WCAG 2.1 Level AA guidelines

### Color Contrast
- Text vs. Background: 4.5:1 minimum (normal text)
- UI Components: 3:1 minimum (icons, borders)
- All semantic colors supplemented with text labels

### Typography
- Minimum font size: 12sp for body text
- Line height: 1.5× for readability
- Letter spacing: Optimized per Material Design

### Focus Indicators
- All interactive elements have visible focus states
- Focus ring: 2dp solid border in #D4A574
- Keyboard navigation fully supported

### Dark Theme Benefits
- Reduces blue light exposure for evening use
- Ideal for extended productivity sessions
- Energy efficient on OLED displays

## Navigation Design

### User Flow
```
Splash Screen (4s)
      ↓
    [Logged In?]
   /           \
 YES            NO
 ↓              ↓
Main App    →  Sign In
 ↓             ↙   ↘
Home    No account? Sign Up
 ↓              ↓
[Tasks, Calendar, Focus, Profile]
 ↓
Dev Info (back to Main)
```

### Screen Transitions
- Splash → Auth: Fade transition (300ms)
- Auth (Sign In ↔ Sign Up): Slide transition (300ms)
- Main App sections: Fade transition (250ms)
- Modal dialogs: Scale + fade (250ms)

## Information Architecture

```
MAIN APP
├── Tasks (Home)
│   ├── View Task List
│   ├── Create Task (modal)
│   ├── Edit Task (modal)
│   └── Delete Task (confirmation)
├── Calendar
│   ├── View Full Month
│   ├── View Day Tasks
│   └── Create Task (modal)
├── Focus Mode
│   ├── 25:00 Timer (Pomodoro)
│   ├── Start/Reset Controls
│   └── Session History (future)
├── User Profile
│   ├── View Profile Info
│   ├── Edit Profile (modal)
│   └── Sign Out (confirmation)
└── Developer Info
    ├── Name, Email, Student ID
    ├── Personal Statement
    ├── Release Version
    └── Exit
```

---

# SLIDE 13: DESIGN TOKENS & BRANDING

## App Identity

**App Name:** TaskFlow  
**Tagline:** "Your Tasks, Your Flow"  
**Logo:** Checkmark + flowing lines design (blue & gold)  
**Version:** 1.0.0

## Design Language

**Core Values:**
- **Clarity:** Information hierarchy is immediately apparent
- **Focus:** Minimalist UI reduces cognitive load
- **Reliability:** Consistent patterns build user confidence
- **Accessibility:** Inclusive design for all users

## Component Library

### Reusable Buttons
```
Primary:     Blue background, white text, 48dp height
Secondary:   Dark gray background, white text, 48dp height
Text:        Transparent, gold text (tertiary actions)
Destructive: Red background, white text (sign out, delete)
Disabled:    Gray background, opacity 0.6
```

### Form Components
```
Text Input:    56dp height, 12dp padding, rounded corners
Date Picker:   Dropdown with modal calendar selector
Checkbox:      24dp square, custom check animation
Radio Button:  24dp circle, custom fill animation
```

### Navigation
```
Bottom Nav:    5 sections, icons + labels, gold active state
Top Bar:       Profile widget + menu icon
Breadcrumb:    Back button for modal/nested views
Fab:          56dp circular, gold background, icon-only
```

### Feedback
```
Snackbar:      Bottom sheet, 2-4 second duration, swipe-dismiss
Dialog:        Modal overlay, scrim background, scale animation
Toast:         Quick notification, auto-dismiss
Loading:       Spinner animation (circular progress)
```

---

# SLIDE 14: DEVELOPMENT HANDOFF NOTES

## Design-to-Development Checklist

### Before Implementation
- ✅ All screen dimensions finalized (393×852dp)
- ✅ Color palette exported (#1A1A1A, #2D5BA8, #D4A574, etc.)
- ✅ Typography scales defined (10sp-32sp)
- ✅ Component library documented (buttons, inputs, cards)
- ✅ Animation specs provided (durations, easing functions)
- ✅ Navigation flow mapped (state machine ready)

### During Development
- Use Material Design 3 as base system
- Apply dark theme consistently across all screens
- Implement all accessibility requirements (48dp touch targets, 4.5:1 contrast)
- Test on multiple screen sizes (small, normal, large phones)
- Validate keyboard navigation and screen reader support

### Quality Assurance
- Compare final UI pixel-by-pixel against design
- Verify all animations match specifications
- Test accessibility with screen readers (Talkback)
- Validate performance (smooth scrolling, no janky animations)
- Test on dark & light ambient conditions

---

# SLIDE 15: FUTURE ENHANCEMENTS & ROADMAP

## Planned Features (Post-MVP)

### Version 1.1
- [ ] Push notifications for task reminders
- [ ] Task categories/tags system
- [ ] Priority levels (High, Medium, Low)
- [ ] Recurring tasks (daily, weekly, monthly)
- [ ] Dark mode toggle (light theme variant)

### Version 1.2
- [ ] Cloud sync (Firebase or similar)
- [ ] Export tasks (PDF, CSV format)
- [ ] Collaborative task lists (shared projects)
- [ ] Advanced analytics (productivity insights)
- [ ] Widget support (home screen task widget)

### Version 2.0
- [ ] AI-powered task suggestions
- [ ] Voice input for tasks
- [ ] Integration with calendar apps (Google Calendar)
- [ ] Desktop app (Kotlin Multiplatform)
- [ ] Social sharing features

## Design Iteration Plan

1. **Gather User Feedback** (V1.0 launch)
2. **Analyze Usage Metrics** (task completion rates, screen time)
3. **Conduct User Testing** (5-10 participants)
4. **Refine UI/UX** based on feedback
5. **Implement High-Impact Changes** in next release cycle

---

# CONCLUSION

TaskFlow represents a modern, focused approach to task management for students and professionals. By combining a clean dark theme, intuitive navigation, and powerful productivity features (Pomodoro timer, calendar integration), we create an application that helps users manage their workflow efficiently.

The design system is modular, extensible, and accessible—providing a strong foundation for future enhancements while maintaining user trust and satisfaction.

---

**Design Completed:** May 2026  
**Implementation Status:** Ready for development  
**Approval:** ✓ Final design specifications approved

---

# APPENDIX: DESIGN ASSETS

## Color Palette Codes
```
#1A1A1A - Primary Background
#2D2D2D - Surface/Card Background
#2D5BA8 - Primary Blue (Brand)
#D4A574 - Gold Accent (Brand)
#FFFFFF - Text Primary
#B3FFFFFF - Text Secondary (70% opacity)
#E74C3C - Error/Destructive
#27AE60 - Success
#F1C40F - Warning
#3498DB - Info
```

## Typography Scale (Kotlin)
```kotlin
// Display
TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)

// Headline
TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)

// Title
TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

// Body Large
TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)

// Body
TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)

// Label
TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)

// Caption
TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Regular)
```

## Dimension Values (dimens.xml)
```xml
<!-- Spacing -->
<dimen name="spacing_4">4dp</dimen>
<dimen name="spacing_8">8dp</dimen>
<dimen name="spacing_12">12dp</dimen>
<dimen name="spacing_16">16dp</dimen>
<dimen name="spacing_24">24dp</dimen>

<!-- Component Heights -->
<dimen name="button_height">48dp</dimen>
<dimen name="input_field_height">56dp</dimen>
<dimen name="fab_size">56dp</dimen>
<dimen name="bottom_nav_height">60dp</dimen>

<!-- Corner Radius -->
<dimen name="corner_radius_small">4dp</dimen>
<dimen name="corner_radius_medium">8dp</dimen>
<dimen name="corner_radius_large">16dp</dimen>
<dimen name="corner_radius_button">28dp</dimen>
```

---

**End of Presentation**

*For questions or clarifications regarding design specifications, contact: 2021t01222@stu.cmb.ac.lk*
