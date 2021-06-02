package be.yellowduck.sparkfw

import java.util.concurrent.atomic.AtomicInteger

class UserDao {

    val users = hashMapOf(
        1 to User(name = "Alice", email = "alice@alice.kt", id = 1),
        2 to User(name = "Bob", email = "bob@bob.kt", id = 2),
        3 to User(name = "Carol", email = "carol@carol.kt", id = 3),
        4 to User(name = "Dave", email = "dave@dave.kt", id = 4)
    )

    var lastId: AtomicInteger = AtomicInteger(users.size - 1)

    fun save(name: String, email: String) : User {
        val id = lastId.incrementAndGet()
        val user = User(name = name, email = email, id = id)
        users.put(id, user)
        return user
    }

    fun findById(id: Int): User? {
        return users[id]
    }

    fun findByEmail(email: String): User? {
        return users.values.find { it.email == email }
    }

    fun update(id: Int, name: String, email: String): User {
        val user = User(name = name, email = email, id = id)
        users.put(id, user)
        return user
    }

    fun delete(id: Int) {
        users.remove(id)
    }

}
