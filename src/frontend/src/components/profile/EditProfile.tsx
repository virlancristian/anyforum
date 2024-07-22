export default function EditProfile() {
    return (
        <div className="w-11/12 bg-black mt-5 h-5/6 p-3">
            <h1 className="text-white text-3xl font-bold mb-10">Edit profile</h1>
            <div className="flex flex-row">
                <p className="text-white font-bold text-2xl mr-5">Username</p>
                <input type="text" className="bg-gray-600 outline-none pl-1 h-10 w-64 text-white font-semibold text-lg"/>
            </div>
            <div>
                <p>About me</p>
                <textarea></textarea>
            </div>
            <div>
                <p>Profile picture</p>
                <input type="file" accept="image/jpeg, image/png, image/gif"/>
            </div>
        </div>
    )
}