package be.yellowduck.sparkfw

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import spark.Request
import spark.Spark.*

fun Request.qp(key: String): String = this.queryParams(key)

fun main() {

    val userDao = UserDao()
    val mapper = jacksonObjectMapper()

    after({ request, response ->
        response.header("Content-Encoding", "gzip")
    })

    get("/hello") { req, res ->
        "Hello World"
    }

    path("/users") {

        get("") { req, res ->
            mapper.writeValueAsString(userDao.users.values)
        }

        get("/:id") { req, res ->
            userDao.findById(req.params("id").toInt())?.toJson()
        }

        get("/email/:email") { req, res ->
            userDao.findByEmail(req.params("email"))?.toJson()
        }

        post("/create") { req, res ->
            val user = userDao.save(name = req.qp("name"), email = req.qp("email"))
            res.status(201)
            user.toJson()
        }

        patch("/update/:id") { req, res ->
            userDao.update(
                id = req.params("id").toInt(),
                name = req.qp("name"),
                email = req.qp("email")
            )?.toJson()
        }

        delete("/delete/:id") { req, res ->
            userDao.delete(req.params("id").toInt())
            "ok"
        }

    }

}
