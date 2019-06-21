package com.example.blocknotische.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ModelDao {

    @Query("SELECT * FROM NotesModel")
    fun getData(): Single<List<NotesModel>>

    @Query("SELECT * FROM NotesModel WHERE id = :itemId ")
    fun getItemById(itemId: Int): Single<NotesModel?>

/*    @Insert
    fun createRow(title: String, body: String, color: Int): Completable */
    @Insert
    fun createRow(model: NotesModel): Completable

    @Query("UPDATE NotesModel SET title=:title, body =:body, color = :color WHERE  id = :id")
    fun updateRow(title: String, body: String, color: Int?, id: Long?): Completable

    @Query("DELETE FROM NotesModel WHERE id = :id")
    fun deleteRow(id: Long): Completable

}