import { ChangeEvent } from "react";

import '../../css/form/form-template.css';

export default function FormTemplate({ item, changeItemField, inputTypeMapper, inputLabelMapper }: 
                                     { item: any, changeItemField: 
                                        (event: ChangeEvent<any>) => void, 
                                        inputTypeMapper: any,
                                        inputLabelMapper: any
                                     }) {
    return (
        <div className="flex flex-col items-center" id="form-template">
            {
                Object.keys(item).map((key: any) => (
                    <>
                        {
                            item[key] !== undefined &&   
                            <div className="flex flex-col justify-between w-full">
                                <label 
                                    htmlFor={key} 
                                    className="text-white font-bold text-xl my-3">
                                        {inputLabelMapper[key]}
                                </label>
                                <input 
                                    type={inputTypeMapper[key]} 
                                    name={key} 
                                    onChange={changeItemField}
                                    className="bg-black bg-opacity-50 text-white font-semibold text-xl outline-none h-12 pl-3"/>
                            </div>
                        }
                    </>
                ))
            }
        </div>
    )
}