# BeadMaker

##### A Java program for creating bead sprites using fuse beads like Perler and Artkal. Additional brands can be added through additional palette XML files.

##### To install, download the <a href="https://github.com/stone-j/BeadMaker/blob/master/exe%20build%20resources/InstallPixelPerfect.exe?raw=true">Windows installer</a>. Then double-click PixelPerfect.exe from the installation folder you chose. Enjoy!

##### How-to video: https://youtu.be/x_SNjAIZV1c




### Development

- Java version: Prefer JDK 21 LTS. If blocked by dependencies, JDK 17 is acceptable.
- Branching: Default branch is `main`. Use short-lived feature branches and open PRs.
- Conventional Commits: Required for all commits (see `CONTRIBUTING.md`).
- Code style: See `.editorconfig` and `CONTRIBUTING.md` for guidelines.

### Build and run (Gradle)

```bash
# Build
./gradlew build

# Run
./gradlew run

# Create distribution zip (bin + libs)
./gradlew distZip

# Test & Coverage
./gradlew test jacocoTestReport
```

### CI

[![CI](https://github.com/nikosalonen/BeadMaker/actions/workflows/ci.yml/badge.svg)](https://github.com/nikosalonen/BeadMaker/actions/workflows/ci.yml)

GitHub Actions runs build and tests on Windows, macOS, and Linux with Java 21.

### Packaging

- Developer distribution (zip):
```bash
./gradlew distZip
```
- Tagged releases publish the distribution zip automatically.
- Native installers (jlink/jpackage) are planned; current builds ship as a portable zip.
