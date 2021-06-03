const neo4j = require('neo4j-driver')

const driver = neo4j.driver("bolt://http://94.74.64.8:7474", neo4j.auth.basic("neo4j", "uvgproy123@"))
const session = driver.session()
const personName = 'Alice'

try {
  const result = await session.run(
    'CREATE (a:Person {name: $name}) RETURN a',
    { name: personName }
  )

  const singleRecord = result.records[0]
  const node = singleRecord.get(0)

  console.log(node.properties.name)
} finally {
  await session.close()
}

// on application exit:
await driver.close()