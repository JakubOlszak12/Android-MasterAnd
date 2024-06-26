package com.example.masterand.ViewModels

import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.masterand.Entity.Profile
import com.example.masterand.R
import com.example.masterand.Repo.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    suspend fun getProfileById(id: Long) {
        val profile = profileRepository.getProfileById(id)
        profileId.value = profile.id
        name.value = profile.name
        email.value = profile.email
        score.value = profile.score
        numberOfColors.value = profile.numberOfColors
        profileImageUri.value = profile.profileImageUri
    }


    var profileId = mutableLongStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val score = mutableIntStateOf(0)
    val numberOfColors = mutableIntStateOf(5)
    val profileImageUri =
        mutableStateOf(Uri.parse("android.resource://com.example.masterand/${R.drawable.baseline_person_24}"))

    suspend fun saveProfile() {
        val profile = profileRepository.getProfileByEmail(email.value)
        if (profile == null) {
            val newProfileId = profileRepository.insertProfile(
                Profile(
                    name = name.value,
                    email = email.value,
                    score = 0,
                    numberOfColors = numberOfColors.value,
                    profileImageUri = profileImageUri.value
                )
            )
            profileId.value = newProfileId
        } else {
            val updatedProfile = Profile(
                id = profile.id,
                name = name.value,
                email = profile.email,

                score = profile.score,
                numberOfColors = numberOfColors.value,
                profileImageUri = profileImageUri.value
            )
            profileRepository.updateProfile(updatedProfile)
            profileId.value = profile.id
        }
    }

    suspend fun updateProfile() {
        profileRepository.updateProfile(
            Profile(
                id = profileId.value,
                email = email.value,
                name = name.value,
                numberOfColors = numberOfColors.value,
                profileImageUri = profileImageUri.value,
                score = score.value,

            )
        )
    }

    suspend fun updateScore() {
        val profile = profileRepository.getProfileById(profileId.value)
        if (profile.score == 0 && score.value > 0) {
            updateProfile()
        } else if (profile.score > score.value) {
            updateProfile()
        }
    }

    fun getAllProfiles(): Flow<List<Profile>> {
        val profiles = profileRepository.getAllProfilesStream()
        return profiles
    }
}