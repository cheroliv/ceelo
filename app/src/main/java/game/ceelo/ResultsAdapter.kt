package game.ceelo

import android.annotation.SuppressLint
import android.util.Log.i
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import game.ceelo.Constant.GAME_TYPE
import game.ceelo.Constant.PLAYER_ONE_NAME
import game.ceelo.Constant.PLAYER_TWO_NAME
import game.ceelo.Game.firstPlayer
import game.ceelo.Game.secondPlayer
import game.ceelo.Hand.compareHands
import game.ceelo.R.id.*
import game.ceelo.R.layout.simple_game_row
import game.ceelo.ResultsAdapter.CeeloViewHolder

class ResultsAdapter(
    var games: List<List<List<Int>>>
) : Adapter<CeeloViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CeeloViewHolder(
        from(parent.context).inflate(
            simple_game_row,
            parent,
            false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: CeeloViewHolder,
        position: Int
    ) = games[position].run {
        holder.run {
            game_id.text = (position + 1).toString()
            player_one_name.text = PLAYER_ONE_NAME
            player_one_dices_throw.text = firstPlayer().toString()
            player_one_result.text = firstPlayer().compareHands(secondPlayer()).apply {
                //TODO:  room persist
                i(javaClass.simpleName,"TODO: room persist")
            }.toString()
            player_one_game_type.text = GAME_TYPE
            player_two_name.text = PLAYER_TWO_NAME
            player_two_dices_throw.text = secondPlayer().toString()
            player_two_result.text = secondPlayer().compareHands(firstPlayer()).toString()
            player_two_game_type.text = GAME_TYPE
        }

    }

    override fun getItemCount() = games.size


    class CeeloViewHolder(
        itemView: View,
        var game_id: TextView = itemView.findViewById(game_id_text),
        var player_one_name: TextView = itemView.findViewById(player_one_name_text),
        var player_one_dices_throw: TextView = itemView.findViewById(player_one_dices_throw_text),
        var player_one_result: TextView = itemView.findViewById(player_one_result_text),
        var player_one_game_type: TextView = itemView.findViewById(player_one_game_type_text),
        var player_two_name: TextView = itemView.findViewById(player_two_name_text),
        var player_two_dices_throw: TextView = itemView.findViewById(player_two_dices_throw_text),
        var player_two_result: TextView = itemView.findViewById(player_two_result_text),
        var player_two_game_type: TextView = itemView.findViewById(player_two_game_type_text),
    ) : ViewHolder(itemView)
}