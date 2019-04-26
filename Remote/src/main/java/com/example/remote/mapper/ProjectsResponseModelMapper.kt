package com.example.remote.mapper

import com.example.data.model.ProjectEntity
import com.example.remote.model.ProjectModel
import javax.inject.Inject

class ProjectsResponseModelMapper @Inject constructor() : ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(projectModel: ProjectModel): ProjectEntity {
        return ProjectEntity(
            projectModel.id,
            projectModel.name,
            projectModel.fullName,
            projectModel.starCount.toString(),
            projectModel.dateCreated,
            projectModel.owner.ownerName,
            projectModel.owner.ownerAvatar,
            false
        )
    }
}