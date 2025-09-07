### BeadMaker Modernization Plan

This checklist breaks modernization into phases you can tackle incrementally. Use Conventional Commits for all changes. Check off items as you go.

How to use:
- Work in short-lived feature branches; open PRs with CI checks.
- Prefer Java 21 LTS (fallback: 17) unless blocked by dependencies.
- Keep app functional at all times; avoid big-bang rewrites.

---

### Phase 0 — Baseline and housekeeping
- [x] Protect `master` (or rename to `main`) and require PR checks
- [x] Add `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`
- [x] Add `.editorconfig` and document code style
- [x] Enforce Conventional Commits (commitlint + CI)
- [x] Decide minimum Java version (21 preferred) and document

Acceptance
- [x] Contrib docs merged; commit linting enforced; local build works on chosen JDK

---

### Phase 1 — Build system and dependencies
- [x] Migrate from Eclipse project to Gradle (single project to start)
- [x] Define dependencies with versions; pin Processing and PDF libs
- [x] Add SLF4J + Logback; replace `System.out` with logging
- [x] Confirm Processing PDF backend (no iText); keep `org.processing:pdf`
- [x] Add application plugin tasks: `run` and distribution (`distZip`)

Acceptance
- [x] `./gradlew build run` works; runnable app via `distZip`

---

### Phase 2 — CI and quality gates
- [x] GitHub Actions workflow: Windows + macOS + Linux build matrix
- [x] Add SpotBugs, Checkstyle, and Spotless (formatting) to build
- [x] Add JUnit 5 test setup and JaCoCo coverage report
- [x] Dependabot for Gradle and GitHub Actions

Acceptance
- [ ] CI green on PRs; status checks required to merge

---

### Phase 3 — Packaging and release
- [ ] Use `jlink` to build trimmed runtime image (deferred)
- [ ] Use `jpackage` to produce platform installers (deferred)
- [x] Automate tag-based releases that upload distribution zip
- [ ] Optional: code signing for Windows installer

Acceptance
- [x] `./gradlew distZip` produces distribution zip; release workflow publishes on tag

---

### Phase 4 — Architecture hardening (non-breaking)
- [ ] Wrap `InterObjectCommunicator` with a typed event-bus interface and migrate gradually
- [ ] Introduce `ConfigService` around XML read/write with schema validation and defaults
- [ ] Separate packages by responsibility: `core` (algorithms), `ui` (Swing), `export` (PDF/PNG)
- [ ] Replace ad-hoc paths with a `PathsConfig` abstraction

Acceptance
- [ ] Modules or packages compile cleanly; features unchanged; logs structured

---

### Phase 5 — Testing and reliability
- [ ] Add unit tests for palette XML parse/serialize round-trip
- [ ] Add tests for color distance and bead mapping
- [ ] Add golden-image tests for dithering on small fixtures
- [ ] Add headless UI smoke test (render one frame offscreen)

Acceptance
- [ ] Tests run in CI; minimal coverage threshold met; failures block PRs

---

### Phase 6 — UX and accessibility
- [ ] HiDPI/scale-friendly rendering; verify fonts and icons
- [ ] Surface keyboard shortcuts (from `Hotkeys.txt`) in UI tooltips/menus
- [ ] Add theme toggle (light/dark) via LookAndFeel; persist in config
- [ ] Show progress for long-running tasks; avoid blocking EDT

Acceptance
- [ ] Crisp on HiDPI; shortcuts visible; theme selection persists

---

### Phase 7 — Performance and stability
- [ ] Profile with JFR on representative images
- [ ] Optimize hotspots (color mapping, dithering); reuse buffers
- [ ] Add safe parallelism in `core` (e.g., `ForkJoinPool`), avoid UI thread work

Acceptance
- [ ] Measurable speedups without regressions; UI remains responsive

---

### Phase 8 — Data integrity and palettes
- [ ] Create XSD for palette XML and validate in CI
- [ ] Add a small verifier tool for palette coverage/duplicates
- [ ] Implement config migration/versioning and backup strategy

Acceptance
- [ ] CI fails on invalid palettes; config upgrades are non-destructive

---

### Phase 9 — Documentation
- [ ] Expand `README.md` with build/run/package instructions and screenshots
- [ ] Add developer docs: module map, architecture notes, release guide
- [ ] Document event-bus adapter and config service designs

Acceptance
- [ ] Docs current and linked from `README.md`

---

### Stretch goals (later)
- [ ] Evaluate JavaFX for selective components or future UI migration
- [ ] Plugin system for palettes and export backends
- [ ] Cross-platform installers in CI matrix
- [ ] Optional GPU acceleration for image steps (OpenCL/JOCL)

---

### Risks and mitigations
- Processing vs Java 21/modules: keep classpath-based initially; modularize later
- PDF backend parity: add regression tests and visual checks on sample projects
- Installer migration: provide user migration notes from legacy installer

---

### Conventional Commits examples
- feat(ui): add dark theme and persist selection
- refactor(core): extract color mapping into service
- build(gradle): add OpenPDF and remove iText
- ci: add Windows/macOS/Linux build matrix
- perf(core): parallelize dithering on large images
- fix(export): correct page margins in PDF output
- docs: add developer setup and release guide
- chore: apply spotless formatting

---

### Suggested timeline (indicative)
- Weeks 1–2: Phases 0–2
- Weeks 3–4: Phase 3
- Weeks 5–6: Phase 4
- Weeks 7–8: Phases 5–6
- Weeks 9–10: Phases 7–9


