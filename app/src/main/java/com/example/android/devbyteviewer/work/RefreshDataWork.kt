/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.devbyteviewer.database.getDatabase
import com.example.android.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException

// TODO (01) Create the RefreshDataWorker class, extend it from CoroutineWorker, and
// pass in a Context and WorkerParams.

// TODO (02) Override the required doWork() method, and create variables for the
// database and the repository.

// TODO (03) Inside doWork(), in a try-catch block, refresh the videos, and
// use Payload() to return SUCCESS or RETRY result.

class RefreshDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            VideosRepository(getDatabase(applicationContext)).refreshVideos()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}