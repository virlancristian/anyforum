import Role from "../../models/authorization/Role";
import UserData from "../../models/user/UserData";

export default function ProfileFrame({ userData, roles }: { userData: UserData | null, roles: Role[] }) {
    if(userData === null) {
        return <p>Not available</p>
    }

    return (
        <div className="w-3/12 p-3">
           <h2 className="w-full bg-gray-800 text-white text-2xl font-bold p-2">{userData.username}</h2>
           <div className="w-full h-96 flex justify-center items-center bg-gray-100 bg-opacity-10">
                <img src={userData.profilePictureURL} className="max-h-full max-w-full"/>
           </div>
           {
                (userData.isBanned || userData.isMuted) && (
                    <div className="w-full bg-gray-800 border-b-2 border-gray-600 py-2 flex flex-row">
                        {userData.isMuted && (<p className="text-white font-bold text-xl w-fit rounded bg-red-600 p-1 mx-3">Muted</p>)}
                        {userData.isBanned && (<p className="text-white font-bold text-xl w-fit rounded bg-red-600 p-1 mx-3">Banned</p>)}
                    </div>
                )
           }
           <div className="w-full grid grid-cols-2 bg-gray-800 p-2 border-b-2 border-gray-600">
                <p
                    className="text-white font-bold text-xl"
                    style={{gridColumn: `1/2`}}>
                    Roles
                </p>
                <div style={{gridColumn: `2/2`}}>
                    {
                        roles.map((role: Role) => (<p className="text-white text-xl w-fit p-1 rounded font-semibold my-1" style={{background: `${role.color}`}}>{role.name}</p>))
                    }
                </div>
           </div>
           <div className="w-full grid grid-cols-2 bg-gray-800 p-2">
                <p
                    className="text-white font-bold text-xl" 
                    style={{gridColumn: '1/2'}}>
                        Joined at
                </p>
                <p
                    className="text-gray-300 font-bold text-xl"
                    style={{gridAutoColumns: `2/2`}}>{userData.joinedAt}</p>
           </div>
        </div>
    )
}