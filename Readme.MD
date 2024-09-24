# Schedule Viewer

## Compilation & Running

Pre-requisites: Oracle Java SE 23 (Because the specification said SE8+). Might run on older versions, but NOT tested.

### Using provided releases
1. Download pre-compiled package from [Releases](https://github.com/Toonu/Schedule-Viewer/releases).
2. Extract anywhere.
3. Go into the folder and either use provided example schedule `program_udrzby.txt` or insert yours.
4. Run `java ScheduleViewer program_udrzby.txt` with optional arguments.
### Manual
1. Download repository folder.
2. Go inside the ScheduleViewer src folder (where ScheduleViewer.java is).
3. Run `javac ScheduleViewer.java` to compile.
4. Go into the folder and insert yours `filename.txt` schedule (or move `program_udrzby` from the resources' folder.
5. Run `java ScheduleViewer filename.txt` with optional arguments. 

### Arguments
- -d | `-delimiter ;` --chooses delimiter (use "" if the delimiter is console keyword);
- -a | `-abort true` --chooses if you want to abort the calendar generation on error on any input line.

## Troubleshooting
- Wrong special (czech) characters on Windows OS in different than Czech locale.
1. In case of using zsh or other shell on UNIX type system, you can ignore this bug.
2. In case your cmd / PowerShell refuses to display UTF czech characters properly
3. you may use `chcp 852` or `chcp 65001` command to TEMPORARILY change your charset locale to view czech (or other, depending on the language) character,
4. do not forget to roll back to `chcp 437`, OR your old charset value which you can check by running `chcp`.
5. Failure to roll the temporary solution back may result in other application trouble.

###### Oh boy, why does Windows Console still not use Unicode...