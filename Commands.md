# Some important and interesting Linux command for troubleshooting JVM processes.


My own learning resource

Some interesting commands for evaluating Java application performance and should be remembered are here-

$jcmd - to take heap dump
$jcmd : gives the list of java processes running.
$jcmd <pid> GC.heap_dump :: generates heap dump in .hprof file

$jps  also lists current running jvm processes

$jstat -gccapacity <pid>
$jstat -gcutil <pid>
$jstat -gc <pid>
$jstat -gccause <pid>

$jmap -histo[live,] <pid> | less :: live objects histgram
$jmap -dump:[live,] file=/path/file <pid> :: heap dump

### Finding where CPU is spending time-

$top -H -p <PID>
[-H all threads with cpu usage]
//$printf "%X \n" 6474 [covert number to hexadecimal format]

search for nid with what we got in second step. See where is thread taking the cpu.
$ printf "%X \n" 6474 [covert number to hexadecimal format]
$ echo $((16#FF)) :: gives 255
$ jcmd <psId> Thread.print > threadDump.txt [take thread dump]


$ printf "%d\n" 0xFF
255
$echo $((16#FF))
255
