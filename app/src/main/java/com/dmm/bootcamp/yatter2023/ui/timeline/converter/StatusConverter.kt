import com.dmm.bootcamp.yatter2023.domain.model.Status
import com.dmm.bootcamp.yatter2023.ui.timeline.StatusBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.converter.MediaConverter

object StatusConverter {
    fun convertToBindingModel(statusList: List<Status>): List<StatusBindingModel> =
        statusList.map { convertToBindingModel(it) }

    fun convertToBindingModel(status: Status): StatusBindingModel =
        StatusBindingModel(
            id = status.id.value,
            displayName = status.account.displayName ?: "",
            username = status.account.username.value,
            avatar = status.account.avatar.toString(),
            content = status.content,
            attachmentMediaList = MediaConverter.convertToDomainModel(status.attachmentMediaList)
        )
}