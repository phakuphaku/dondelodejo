package dondelodejo.com

import grails.transaction.Transactional

@Transactional
class LoggerService {

	
    def static Log(String aLoguear) {
		println new Date().toString()+"I: "+aLoguear
    }
	def static Log( char letra,String aLoguear) {
		println letra+": "+ aLoguear
	}
}
