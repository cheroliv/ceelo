package game.ceelo

import webapp.models.JwtToken
import webapp.models.ProblemsModel
import webapp.models.Signup

abstract class CeeloServiceKtor : CeeloService {

//    suspend fun register(signup: Signup):ProblemsModel? {
//        TODO("Not yet implemented")
//    }
    override suspend fun authenticate(login: String, password: String):Pair<ProblemsModel,JwtToken> {
        TODO("Not yet implemented")
    }
}