# Lamdas, Stream API of Java 8 // Code Smells Java8 can fix
1. Anonymous Inner type
list.sort(new Comparator<String>() {
public int compare (String o1, String o2) {
return o1.length() - o2.length();
}
});
can be changed to 
list.sort((o1,o2) -> o1.length() - o2.length());

2. Comparators
list.sort(Comparator.comparingInt(String::length));
list.sort(Comparator.comparingInt(String::length).reversed());

3. Classes with no states
Often you come across classes with names ending in Util or Helper that contain static methods but no state of their own. Now that interfaces support static methods, these classes may be better as interfaces so no one can accidentally sneak state into a type that is only meant to contain functions.
Single abstract method only ? think of converting it to functional interface.

4. Nested for/if statements
Use streamsapi while working with Collections.
List<Field> validFields = new ArrayList<Field>();
for (Field field : fields) {
if (meetsCriteria(field)) {
validFields.add(field);
}
}
return validFields;
to -
return fields.stream()
.filter(this::meetsCriteria)
.collect(Collectors.toList());
Sometimes, for loops with an inner if statement may

Sometimes, for loops with an inner if statement may
be refactored to anyMatch or findFirst:
for (String current : strings) {
if (current.equals(wanted)) {
return true;
}
}
return false;
…can be replaced with:
return strings.stream()
.anyMatch(current -> current.
equals(wanted));

And:
for (String current : strings) {
if (current.equals(wanted)) {
return current;
}
}
return null;
…can be:
return strings.stream()
.filter(current -> current.
equals(wanted))
.findFirst()
.orElse(null);

5. Multiple Operations on a Collection
// collect messages for logging
List<LogLine> lines = new ArrayList<>();
for (Message message : messages) {
lines.add(new LogLine(message));
}
// sort
Collections.sort(lines);
// log them
for (LogLine line : lines) {
line.log(LOG);
}
Convert it to 
messages.stream()
.map(LogLine::new)
.sorted()
.forEach(logLine -> logLine.log(LOG));

6 Using an Iterator to Remove Elements
Iterator<String> iterator = strings.iterator();
while (iterator.hasNext()) {
String current = iterator.next();
if (current.endsWith(“jnilib”)) {
iterator.remove();
convert to
strings.removeIf (current -> current.endsWith(“jnilib”));

7. Null Checks
NullPointerExceptions are the bane of a Java developer’s life, and it’s not uncommon to see null checks scattered around the code just to make sure we don’t encounter one. The introduction of Optional means we can be much more explicit about the expected return types of a method and eliminate unnecessary null checks.
public static String findString (String wanted)
{
List<String> strings = new ArrayList<>();
return strings.stream()
.filter(current -> current.
equals(wanted))
.findFirst()
.orElse(null);
}
Any code that called findString would have to check if the value was null, and if so take appropriate action:
String foundString = findString(wantedString);
if (foundString == null) {
return “Did not find value” and
wantedString;
} else {
return foundString;
}
This is ugly and tedious. If we update the findString method to return an Optional:
public static Optional<String> findString
(String wanted) {
List<String> strings = new ArrayList<>();
return strings.stream()
.filter(current -> current.
equals(wanted))
.findFirst();
}
…then we can deal with the case of the value not being found much more elegantly:
return findString (wantedString).

#  Sacalability using Java 9 Project Jigsaw
Java getting competition from Kotlin (mainly for Android development ), Scala, Groovy  - all jvm based languages

--> Scalability
Horizontal or Scaling out - Adding more nodes and redistributing load among them. SOA, microservice and web servers scale out by adding more servers.

Vertical Scaling or Scale up - Adding more computing or memory resources to the same servers. Virtulization.

--> High Availability
Degree of functional continuity with in time window specified.
A = 100 - (100*D/U) D - Unplanned Downtime U - Uptime expressed in minutes.

some standard metrics
90 oe one nine - 90% available or 36.5 days down.
99 or two nince - 99% available or 4 days down
99.9 or three nine - 99.9% available or 8.8 hours down.
99.99 or four nine - 99.99% available or 5.3 min down.
99.999 or five nine - 99.999% available or 32 seconds down.

--> Elasticity
Elasticity is ability to dynamically add or remove resources from the system based on demand. Specilized implementation of Scaling.

--> Scalability implementation rules
1. SLA to scale out or scale up?
2. trading system should scale in real time and within availabilty level.
3. Ecommerce system can scale in during slow months and scale out during holiday seasons.

--> Load Balancing
Techinique to minimise response time and increase throughput by spreading request on two or more resources. It could be implemented as hardware or software
Round robin, least connected, ip hashing etc can be used as algo.
-->Persistent Load Balancer - Stateful application require persistent or sticky load balancer. It require data sharing between nodes. This can be achieved using caching techniques.

