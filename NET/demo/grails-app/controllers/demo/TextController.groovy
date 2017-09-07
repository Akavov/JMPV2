package demo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TextController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Text.list(params), model:[textCount: Text.count()]
    }

    def show(Text text) {
        respond text
    }

    def create() {
        respond new Text(params)
    }

    @Transactional
    def save(Text text) {
        if (text == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (text.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond text.errors, view:'create'
            return
        }

        text.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'text.label', default: 'Text'), text.id])
                redirect text
            }
            '*' { respond text, [status: CREATED] }
        }
    }

    def edit(Text text) {
        respond text
    }

    @Transactional
    def update(Text text) {
        if (text == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (text.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond text.errors, view:'edit'
            return
        }

        text.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'text.label', default: 'Text'), text.id])
                redirect text
            }
            '*'{ respond text, [status: OK] }
        }
    }

    @Transactional
    def delete(Text text) {

        if (text == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        text.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'text.label', default: 'Text'), text.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'text.label', default: 'Text'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
