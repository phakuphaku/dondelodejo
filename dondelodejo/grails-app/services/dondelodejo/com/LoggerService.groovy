package dondelodejo.com

import grails.transaction.Transactional

@Transactional
class LoggerService {
	def static Log(Map map) {
		this.Log(map.toString())
	}
    def static Log(String aLoguear) {
		if (loggerEstaActivo()) {
			println new Date().toString()+" INFO: "+aLoguear
		};
    }
	def static Log( char letra,String aLoguear) {
		println letra+": "+ aLoguear
	}
	private static boolean loggerEstaActivo(){
		return true
	}
}
