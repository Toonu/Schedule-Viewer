# Schedule Viewer

## Compilation & Running

### Default
1. Download pre-compiled package from [Releases](https://github.com/Toonu/Schedule-Viewer/releases).
### Manual
1. Download repository folder.
2. While inside the ScheduleViewer src folder.
3. Run `javac ScheduleViewer.java`
## Running
- If you've chosen the Production.zip from [Releases](https://github.com/Toonu/Schedule-Viewer/releases).
1. Unzip & Navigate yourself into the Production folder.
2. Insert your text file `filename.txt` into the ScheduleViewer folder.
3. Run `java ScheduleViewer filename.txt` with optional arguments.
- If you have downloaded or built the production output.
1. Navigate yourself into out/production/Schedule Viewer folder. (or the location where you compiled the project)
2. Insert your text file `filename.txt` into the folder.
3. Run `java ScheduleViewer filename.txt` with optional arguments.

### Arguments
- -d | `-delimiter ;` --chooses delimiter (use "" if the delimiter is console keyword);
- -a | `-abort true` --chooses if you want to abort the calendar generation on error on any input line.

## Troubleshooting
- Wrong special (czech) characters on Windows OS
1. In case of using zsh or other shell on UNIX type system, you can ignore this bug.
2. In case your cmd / PowerShell refuses to display UTF czech characters properly
3. you may use `chcp 852` or `chcp 65001` command to TEMPORARILY change your charset locale to view czech (or other, depending on the language) character,
4. do not forget to roll back to `chcp 437`, OR your old charset value which you can check by running `chcp`.
5. Failure to roll the temporary solution back may result in other application trouble.