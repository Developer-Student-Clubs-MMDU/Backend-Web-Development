Documents in Firestore are sets of key-value pairs. They look like JSON
objects — though it would be more accurate to use the more general
term map to describe them. A map is a set of key-value pairs. For that
reason, we can theoretically store all of the data for a trail in a single
document. Here's an example of how that data structure might look:

{
 name: "Enchantments Trail",
 region: "Enchantments",
 description: "23.6 mile trail through Enchantments near Leavenworth.",
 reviews: {
 1: {
 content: "Amazing!"
 },
 2: {
 content: "Very challenging hike."
 },
 }
}