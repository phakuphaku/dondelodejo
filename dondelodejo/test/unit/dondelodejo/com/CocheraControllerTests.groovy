package dondelodejo.com



import org.junit.*
import grails.test.mixin.*

@TestFor(CocheraController)
@Mock(Cochera)
class CocheraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cochera/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cocheraInstanceList.size() == 0
        assert model.cocheraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cocheraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cocheraInstance != null
        assert view == '/cochera/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cochera/show/1'
        assert controller.flash.message != null
        assert Cochera.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cochera/list'

        populateValidParams(params)
        def cochera = new Cochera(params)

        assert cochera.save() != null

        params.id = cochera.id

        def model = controller.show()

        assert model.cocheraInstance == cochera
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cochera/list'

        populateValidParams(params)
        def cochera = new Cochera(params)

        assert cochera.save() != null

        params.id = cochera.id

        def model = controller.edit()

        assert model.cocheraInstance == cochera
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cochera/list'

        response.reset()

        populateValidParams(params)
        def cochera = new Cochera(params)

        assert cochera.save() != null

        // test invalid parameters in update
        params.id = cochera.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cochera/edit"
        assert model.cocheraInstance != null

        cochera.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cochera/show/$cochera.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cochera.clearErrors()

        populateValidParams(params)
        params.id = cochera.id
        params.version = -1
        controller.update()

        assert view == "/cochera/edit"
        assert model.cocheraInstance != null
        assert model.cocheraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cochera/list'

        response.reset()

        populateValidParams(params)
        def cochera = new Cochera(params)

        assert cochera.save() != null
        assert Cochera.count() == 1

        params.id = cochera.id

        controller.delete()

        assert Cochera.count() == 0
        assert Cochera.get(cochera.id) == null
        assert response.redirectedUrl == '/cochera/list'
    }
}
