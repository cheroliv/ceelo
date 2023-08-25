@file:Suppress("ObjectPropertyName", "MemberVisibilityCanBePrivate")

package game.ceelo

import webapp.models.JwtToken
import webapp.models.ProblemsModel

/**

RULES

Game can be played by minimum 2 player and maximum 4 player.
On startup all user will get chance/turn to roll 1 dice. Highest dice roll wins bank.
If more than 1 player have highest number, players that have the highest number
must roll again to get the highest number. This must continue until it is established who has bank.
Then banker will put Cee Lo bucks in the bank as much as he wants (Not below than mandatory minimum).
All other player will bet as much as he wants.
Then banker get a chance to roll 3 dice. Banker can tap on PUSH (Don't know the exact word) before taking his turn:
Automatic Win: If the banker rolls 4-5-6, "triples" (all three dice show the same number), or a pair (of non-6s) with a 6 then he/she instantly wins all bets.
Automatic Loss: If the banker rolls 1-2-3, or a pair (of non-1s) with a 1, he/she instantly loses all bets (the players break the bank).
Set Point: If the banker rolls a pair and a single (2, 3, 4, or 5), then the single becomes the banker's "point." E.g. a roll of 2-2-4 gives the banker a point of 4. Note that you can not set a point of 1 or 6, as those would result in an automatic loss or win, respectively (see above).
Re-roll: If the dice don't show any of the above combinations, then the banker rolls again and keeps rolling until he/she gets an instant win or an instant loss, or sets a point.
Banker will get limited amount of time to play his turn. If Banker didn't play on that duration then he/she will be timeout and total bet amount will be distributed to remaining users (Because we don't want to put other users for long waiting).
In Automatic Win case banker will get all bet chips amount and new round will be started from step 4.
In Automatic Loss case user will gets as much money as he bets.
In Set Point case banker point will be noted and other user will get chance to roll 3 dice. The player wins with a 4-5-6, triple, or any point higher than the Banker's. They lose with a 1-2-3, or any point lower than the banker's. Player will get limited amount of time to play his turn. If player didn't play his turn on that duration then he/she will be timeout and banker will gets bucks which that player bet on startup (Because we don't want to put other users for long waiting).
If player get triple banker must have to pay double bucks then user has bet (There may be one case that banker don't have enough bucks to pay double). Next round will start from step 4.
If banker pressed PUSH before taking his turn, In case of Triples banker must have to give double bucks then user has bet. Next round will start from step 4.
If  banker press PUSH before taking his turn, In case of Tie banker will win and user have to bet amount bucks. Next round will start from step 4.
If banker haven't press PUSH before taking his turn, In case of Tie (no winner or loser) the banker and the player will get his bet amount back. Next round will start from step 4.
Player can stick with bank by betting same money which is in the bank. In that case only that user and banker will play next game. In this case PUSH rule will not work. All other user have to wait for the next round (We need to think for this case because player comes to play game from all around the world. Player will not like to wait. If only two player is playing the game then the other player will left this table and will find new table from the lobby. This is my thought it depends on you what you want. We will develop game accordingly). Next round will start from step 4.
If Banker gets 4,5,6 then banker will be able to take Cee Lo bucks from the bank to his game account or can add more Cee Lo bucks into bank. Also banker can left the game. If banker left then game will start from step 2 with remaining user.
If player gets 4,5,6 then player gets the bank along with all Cee Lo bucks. (If two player gets 4,5,6 in the same round then no change. Bank remains with the same person). And new round will start from step 4.
If banker lost all his money which is in the bank then game will start from step 2.
 */


interface CeeloService {
    fun allGames(): List<List<List<Int>>>
    fun saveGame(newGame: List<List<Int>>)
    suspend fun authenticate(login: String, password: String):Pair<ProblemsModel,JwtToken>
}