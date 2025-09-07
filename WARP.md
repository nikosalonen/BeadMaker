# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

BeadMaker (branded as "Pixel Perfect") is a Java desktop application for creating bead sprite patterns using fuse beads like Perler and Artkal. The application converts images into pixelated patterns optimized for physical bead construction, with support for multiple bead brands and color palettes.

## Architecture

### Core Components

**BeadMaker.java** - Main application class and entry point that orchestrates the entire application. Contains the main() method and manages the overall application lifecycle, UI layout, and inter-component communication.

**ImageController.java** - Central image processing engine responsible for:
- Loading and preprocessing images
- Applying color corrections, dithering, and bead mapping
- Managing multiple image representations (original, scaled, color-corrected, single-color highlighted)
- Supporting different pegboard modes (Perler, Mini, Custom sizes)
- Handling zoom and scaling operations

**Palette.java** - Color palette management system that:
- Loads bead color definitions from XML files
- Manages multiple bead brands (Perler, Artkal-S, Hama)
- Filters colors by type (pearl, translucent, neutral, grayscale)
- Tracks bead usage counts and minimum bead requirements
- Provides color mapping and selection functionality

**ControlPanel.java** - Main UI control interface featuring:
- Color adjustment sliders (RGB, brightness, contrast, saturation)
- Dithering method selection (Floyd-Steinberg, Jarvis-Judice-Ninke, etc.)
- Brand toggles and palette filters
- Image processing parameters (scale, sharpness, dither level)

**WindowController.java** - Window management and keyboard input handling

### UI Architecture Pattern

The application uses a custom UI component architecture with specialized Swing extensions:
- **BM*** classes (BMSlider, BMTextField, BMCheckBox) - Custom UI components with integrated event handling
- **ControlPanel*** classes - Modular panel system for organized UI layout
- **Palette*** classes - Specialized UI components for color palette display and interaction

### Inter-Component Communication

**InterObjectCommunicator.java** - Central message bus system that enables decoupled communication between components using string-based message passing and event listeners. This allows components to communicate without direct references.

### Data Management

**XMLWorker.java** - Handles all XML-based configuration and data persistence:
- Application configuration (`_default_config.xml`)
- Color palette definitions (`_default_pallette.xml`, etc.)
- Project files (`.pbp` format)

**Palette XML Structure** - Color definitions include RGB values, brand information, bead properties (pearl, translucent), and categorization data.

### Image Processing Pipeline

1. **Input**: Load original image
2. **Preprocessing**: Clean image (replace pure black)
3. **Color Correction**: Apply RGB, brightness, contrast, saturation adjustments
4. **Bead Mapping**: Map colors to available bead palette using weighted color matching
5. **Dithering**: Apply selected dithering algorithm
6. **Rendering**: Generate multiple representations (full render, single-color highlight, tile views)
7. **Output**: Export as PNG or PDF with bead count information

### External Dependencies

- **Processing Core Library** - Used for advanced image processing operations and PImage handling
- **iText Library** - PDF generation for printable bead patterns
- **Java Swing** - Primary UI framework
- **Launch4j** - Windows executable wrapper (configured via `PixelPerfect_Launch4jConfig.xml`)

## Development Setup

### Prerequisites

- **Java 7+** (Java 8 recommended based on installer requirements)
- **Eclipse IDE** (project configured for Eclipse with `.project` and `.classpath`)
- **Processing 3.3.7+** libraries (referenced in classpath)
- **iText library** for PDF export functionality

### Project Structure

```
src/beadMaker/           # Main application source
├── BeadMaker.java       # Application entry point
├── ImageController.java # Image processing engine  
├── Palette.java         # Color palette management
├── ControlPanel.java    # Main UI controls
├── WindowController.java# Window management
├── helpers/            # Utility classes
│   ├── XMLWorker.java  # XML configuration handling
│   └── PDFHelper.java  # PDF export functionality
└── [UI Components]     # Custom Swing components (BM*, Control*, Palette*)

config/                 # Configuration files
├── _default_config.xml # Application settings
└── default_project.pbp # Default project template

pallettes/              # Bead color definitions
├── _default_pallette.xml           # Main color palette
├── _default_pallette_withSorting.xml
└── [Brand-specific palettes]

exe build resources/    # Windows build artifacts
├── PixelPerfect.exe    # Windows executable
├── PixelPerfect.iss    # Inno Setup installer script
└── PixelPerfect_Launch4jConfig.xml # Launch4j configuration
```

## Common Development Commands

### Building the Application

Since this is an Eclipse project without build scripts, compilation is typically done through the Eclipse IDE:

1. **Import Project**: File → Import → Existing Projects into Workspace
2. **Configure Build Path**: Ensure Processing core libraries and iText are on the classpath
3. **Run Configuration**: Main class is `beadMaker.BeadMaker`

### Creating Windows Executable

```bash
# Build JAR file (typically done through Eclipse: Export → Runnable JAR)
# Then use Launch4j to create executable:
launch4j.exe "exe build resources/PixelPerfect_Launch4jConfig.xml"
```

### Creating Windows Installer

```bash
# Use Inno Setup with the provided script:
iscc "exe build resources/PixelPerfect.iss"
```

### Running from Command Line

```bash
# Run directly with Java (ensure classpath includes all dependencies):
java -cp "bin;path/to/processing/core.jar;path/to/itext.jar" beadMaker.BeadMaker
```

## Key Configuration Files

- **`config/_default_config.xml`** - Application preferences, default paths, expert mode setting
- **`pallettes/_default_pallette_withSorting.xml`** - Primary color palette with sorting
- **File associations** - `.pbp` files are associated with the application via Windows registry

## Hotkeys Reference

From `Hotkeys.txt`:
- **C**: Show all colors
- **P**: Toggle Perler brand
- **A**: Toggle Artkal brand  
- **G**: Show/hide grid
- **B**: Show pixels as beads
- **O**: Open file
- **S**: Save / **Ctrl+S**: Save As
- **Q**: Hide/show control panel
- **I**: Select image
- **E**: Export PNG / **D**: Export PDF / **Ctrl+D**: Export color PDF
- **F**: Flip image
- **M**: Toggle expert mode
- **Ctrl+X**: Exit

## Development Notes

- The application uses a custom Inter-Object Communication system for loose coupling between components
- Image processing supports multiple dithering algorithms and color weighting strategies  
- Palette system supports multiple bead brands with filtering capabilities
- PDF export includes both pattern layouts and bead shopping lists
- Application data is stored in `%APPDATA%/Nostalgic Pixels Pixel Perfect/` on Windows
- Project files use custom `.pbp` format for saving work sessions
