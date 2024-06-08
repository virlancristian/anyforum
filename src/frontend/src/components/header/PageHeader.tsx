import UserOptionDrownDown from "./UserOptionDropDown";

export default function PageHeader() {
    return (
        <div className="w-11/12 bg-black bg-opacity-60 text-white h-16 rounded mt-10 flex flex-row justify-between">
            <h1 className="font-bold text-5xl flex justify-center items-center ml-5">AnyTopic</h1>
            <div className="grid grid-cols-2 grid-rows-2">
                <button className="font-bold text-xl hover:bg-opacity-30 hover:bg-gray-600 mr-5 rounded h-12 my-2" style={{ gridColumn: 1/2 }}>
                    Hello, user!
                </button>
                <UserOptionDrownDown />
            </div>
        </div>
    )
}